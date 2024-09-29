<template>
  <div class="container">
    <Breadcrumb :items="['menu.basic', 'menu.basic.blockchain']" />
    <a-card class="general-card" :title="$t('menu.list.searchTable')">
      <a-row>
        <a-col :flex="1">
          <a-form
            :model="formModel"
            :label-col-props="{ span: 6 }"
            :wrapper-col-props="{ span: 18 }"
            label-align="left"
          >
            <a-row :gutter="16">
              <a-col :span="7">
                <a-form-item
                  field="number"
                  :label="$t('table.blockchain.blockchainCode')+'：'"
                >
                  <a-input
                    v-model="formModel.blockchainCode"
                    :placeholder="$t('table.blockchain.blockchainCode.placeholder')"
                  />
                </a-form-item>
              </a-col>
              <a-col :span="7">
                <a-form-item field="name" :label="$t('table.blockchain.blockchainName')+'：'">
                  <a-input
                    v-model="formModel.blockchainName"
                    :placeholder="$t('table.blockchain.blockchainName.placeholder')"
                  />
                </a-form-item>
              </a-col>
              <a-col :span="6">
                <a-form-item
                  field="contentType"
                  :label="$t('table.blockchain.blockchainState')+'：'"
                >
                  <a-select
                    v-model="formModel.blockchainState"
                    :options="blockchainStateOptions"
                    :placeholder="$t('table.blockchain.blockchainState.select')"
                  />
                </a-form-item>
              </a-col>
              <a-col :span="2" :flex="'86px'" style="text-align: right">
                  <a-button type="primary" @click="search">
                    <template #icon>
                      <icon-search />
                    </template>
                    {{ $t('searchTable.form.search') }}
                  </a-button>
              </a-col>
              <a-col :span="2">
                <a-button @click="reset">
                    <template #icon>
                      <icon-refresh />
                    </template>
                    {{ $t('searchTable.form.reset') }}
                  </a-button>
              </a-col>
            </a-row>
          </a-form>
        </a-col>        
      </a-row>
      <a-divider style="margin-top: 0" />
      <a-row style="margin-bottom: 16px">
        <a-col :span="12">
          <a-space>
            <a-button type="primary" @click="toAdd">
              <template #icon>
                <icon-plus />
              </template>
              {{ $t('menu.basic.blockchain.add') }}
            </a-button>
            <a-upload action="/">
              <template #upload-button>
                <a-button>
                  {{ $t('searchTable.operation.import') }}
                </a-button>
              </template>
            </a-upload>
          </a-space>
        </a-col>
        <a-col
          :span="12"
          style="display: flex; align-items: center; justify-content: end"
        >
          <a-button>
            <template #icon>
              <icon-download />
            </template>
            {{ $t('searchTable.operation.download') }}
          </a-button>
          <a-tooltip :content="$t('searchTable.actions.refresh')">
            <div class="action-icon" @click="search"
              ><icon-refresh size="18"
            /></div>
          </a-tooltip>
          <a-dropdown @select="handleSelectDensity">
            <a-tooltip :content="$t('searchTable.actions.density')">
              <div class="action-icon"><icon-line-height size="18" /></div>
            </a-tooltip>
            <template #content>
              <a-doption
                v-for="item in densityList"
                :key="item.value"
                :value="item.value"
                :class="{ active: item.value === size }"
              >
                <span>{{ item.name }}</span>
              </a-doption>
            </template>
          </a-dropdown>
          <a-tooltip :content="$t('searchTable.actions.columnSetting')">
            <a-popover
              trigger="click"
              position="bl"
              @popup-visible-change="popupVisibleChange"
            >
              <div class="action-icon"><icon-settings size="18" /></div>
              <template #content>
                <div id="tableSetting">
                  <div
                    v-for="(item, index) in showColumns"
                    :key="item.dataIndex"
                    class="setting"
                  >
                    <div style="margin-right: 4px; cursor: move">
                      <icon-drag-arrow />
                    </div>
                    <div>
                      <a-checkbox
                        v-model="item.checked"
                        @change="
                          handleChange($event, item as TableColumnData, index)
                        "
                      >
                      </a-checkbox>
                    </div>
                    <div class="title">
                      {{ item.title === '#' ? '序列号' : item.title }}
                    </div>
                  </div>
                </div>
              </template>
            </a-popover>
          </a-tooltip>
        </a-col>
      </a-row>
      <a-table
        row-key="id"
        :loading="loading"
        :pagination="pagination"
        :columns="(cloneColumns as TableColumnData[])"
        :data="renderData"
        :bordered="false"
        :size="size"
        @page-change="onPageChange"
      >
        <template #status="{ record }">
          <span v-if="record.blockchainState == 2" style="background-color: red" class="circle"></span>
          <span v-else-if="record.blockchainState == 1" class="circle pass"></span>
          <span v-else-if="record.blockchainState == 0" class="circle"></span>
          {{ $t(`table.blockchain.blockchainState.${record.blockchainState}`) }}
        </template>
        <template #operations="{ record }">
          <a-button v-permission="['admin']" type="text" size="small" @click="toDetail(record.blockchainId)">
            {{ $t('searchTable.columns.operations.view') }}
          </a-button>
          <a-button v-permission="['admin']" type="text" size="small">
            {{ $t('searchTable.columns.operations.state') }}
          </a-button>
          <a-button v-permission="['admin']" type="text" size="small">
            {{ $t('searchTable.columns.operations.modify') }}
          </a-button>
        </template>
      </a-table>
    </a-card>
  </div>
</template>

<script lang="ts" setup>
  import { computed, ref, reactive, watch, nextTick } from 'vue';
  import { useI18n } from 'vue-i18n';
  import useLoading from '@/hooks/loading';
  import { queryBlockchainPage, Blockchain, BlockchainQueryParams } from '@/api/basic';
  import { Pagination } from '@/types/global';
  import type { SelectOptionData } from '@arco-design/web-vue/es/select/interface';
  import type { TableColumnData } from '@arco-design/web-vue/es/table/interface';
  import cloneDeep from 'lodash/cloneDeep';
  import Sortable from 'sortablejs';
  import { useRouter } from 'vue-router';
  const router = useRouter();
  type SizeProps = 'mini' | 'small' | 'medium' | 'large';
  type Column = TableColumnData & { checked?: true };

  const generateFormModel = () => {
    return {
      blockchainCode: '',
      blockchainName: '',
      blockchainState: -1,
    };
  };
  const { loading, setLoading } = useLoading(true);
  const { t } = useI18n();
  const renderData = ref<Blockchain[]>([]);
  const formModel = ref(generateFormModel());
  const cloneColumns = ref<Column[]>([]);
  const showColumns = ref<Column[]>([]);

  const size = ref<SizeProps>('medium');

  const basePagination: Pagination = {
    current: 1,
    pageSize: 20,
  };
  const pagination = reactive({
    ...basePagination,
  });
  const densityList = computed(() => [
    {
      name: t('searchTable.size.mini'),
      value: 'mini',
    },
    {
      name: t('searchTable.size.small'),
      value: 'small',
    },
    {
      name: t('searchTable.size.medium'),
      value: 'medium',
    },
    {
      name: t('searchTable.size.large'),
      value: 'large',
    },
  ]);
  const columns = computed<TableColumnData[]>(() => [
    {
      title: t('table.blockchain.BlockchainId'),
      dataIndex: 'blockchainId',
    },
    {
      title: t('table.blockchain.blockchainCode'),
      dataIndex: 'blockchainCode',
    },
    {
      title: t('table.blockchain.blockchainName'),
      dataIndex: 'blockchainName',
    },
    {
      title: t('table.blockchain.blockchainMainNetwork'),
      dataIndex: 'blockchainMainNetwork',
    },
    {
      title: t('table.blockchain.blockchainNetworkType'),
      dataIndex: 'blockchainNetworkType',
    },
    {
      title: t('table.blockchain.blockchainState'),
      dataIndex: 'blockchainState',
      slotName: 'status',
    },
    {
      title: t('searchTable.columns.operations'),
      dataIndex: 'operations',
      slotName: 'operations',
    },
  ]);
  const blockchainStateOptions = computed<SelectOptionData[]>(() => [
    {
      label: t('table.blockchain.blockchainState.select'),
      value: -1,
    },
    {
      label: t('table.blockchain.blockchainState.create'),
      value: 0,
    },
    {
      label: t('table.blockchain.blockchainState.enable'),
      value: 1,
    },
    {
      label: t('table.blockchain.blockchainState.disable'),
      value: 2,
    },
  ]);
  const fetchData = async (
    params: BlockchainQueryParams = {
        blockchainCode: formModel.value.blockchainCode, 
        blockchainName: formModel.value.blockchainName,
        blockchainState: formModel.value.blockchainState,
        page: pagination.current + 1,
        size: pagination.pageSize 
      }
  ) => {
    setLoading(true);
    try {
      const { data } = await queryBlockchainPage(params);

      renderData.value = data.records;
      pagination.current = data.current;
      pagination.total = data.total;
    } catch (err) {
      console.log(err);
    } finally {
      setLoading(false);
    }
  };

  const search = () => {
    fetchData();
  };
  const onPageChange = (current: number) => {
    pagination.current = current;
    fetchData();
  };

  fetchData();
  const reset = () => {
    formModel.value = generateFormModel();
  };

  const toAdd = () =>{
    router.push('blockchain/add');
  };

  const toDetail = (blockchainId: number) =>{
    router.push(`blockchain/detail/${blockchainId}`);
  }

  const handleSelectDensity = (
    val: string | number | Record<string, any> | undefined,
    e: Event
  ) => {
    size.value = val as SizeProps;
  };

  const handleChange = (
    checked: boolean | (string | boolean | number)[],
    column: Column,
    index: number
  ) => {
    if (!checked) {
      cloneColumns.value = showColumns.value.filter(
        (item) => item.dataIndex !== column.dataIndex
      );
    } else {
      cloneColumns.value.splice(index, 0, column);
    }
  };

  const exchangeArray = <T extends Array<any>>(
    array: T,
    beforeIdx: number,
    newIdx: number,
    isDeep = false
  ): T => {
    const newArray = isDeep ? cloneDeep(array) : array;
    if (beforeIdx > -1 && newIdx > -1) {
      // 先替换后面的，然后拿到替换的结果替换前面的
      newArray.splice(
        beforeIdx,
        1,
        newArray.splice(newIdx, 1, newArray[beforeIdx]).pop()
      );
    }
    return newArray;
  };

  const popupVisibleChange = (val: boolean) => {
    if (val) {
      nextTick(() => {
        const el = document.getElementById('tableSetting') as HTMLElement;
        const sortable = new Sortable(el, {
          onEnd(e: any) {
            const { oldIndex, newIndex } = e;
            exchangeArray(cloneColumns.value, oldIndex, newIndex);
            exchangeArray(showColumns.value, oldIndex, newIndex);
          },
        });
      });
    }
  };

  watch(
    () => columns.value,
    (val) => {
      cloneColumns.value = cloneDeep(val);
      cloneColumns.value.forEach((item, index) => {
        item.checked = true;
      });
      showColumns.value = cloneDeep(cloneColumns.value);
    },
    { deep: true, immediate: true }
  );
  
</script>

<script lang="ts">
  export default {
    name: 'BlockchainIndex',

  };
</script>

<style scoped lang="less">
  .container {
    padding: 0 20px 20px 20px;
  }
  :deep(.arco-table-th) {
    &:last-child {
      .arco-table-th-item-title {
        margin-left: 16px;
      }
    }
  }
  .action-icon {
    margin-left: 12px;
    cursor: pointer;
  }
  .active {
    color: #0960bd;
    background-color: #e3f4fc;
  }
  .setting {
    display: flex;
    align-items: center;
    width: 200px;
    .title {
      margin-left: 12px;
      cursor: pointer;
    }
  }
</style>
