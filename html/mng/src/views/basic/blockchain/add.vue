<template>
  <div class="container">
    <Breadcrumb :items="['menu.basic', 'menu.basic.blockchain', 'menu.basic.blockchain.add']" />
    <a-form ref="formRef" layout="vertical" :model="formData">
      <a-space direction="vertical" :size="16">
        <a-card class="general-card">
          <template #title>
            {{ $t('form.blockchain.title') }}
          </template>
          <a-row :gutter="80">
            <a-col :span="8">
              <a-form-item :label="$t('form.blockchain.blockchainCode')" field="blockchainCode">
                <a-input v-model="formData.blockchainCode" :placeholder="$t('form.blockchain.blockchainCode.placeholder')
                  ">
                </a-input>
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item :label="$t('form.blockchain.blockchainName')" field="blockchainName">
                <a-input v-model="formData.blockchainName" :placeholder="$t('form.blockchain.blockchainName.placeholder')
                  ">
                </a-input>
              </a-form-item>
            </a-col>
          </a-row>
          <a-row :gutter="80">
            <a-col :span="8">
              <a-form-item :label="$t('form.blockchain.blockchainMainNetwork')" field="blockchainMainNetwork">
                <a-input v-model="formData.blockchainMainNetwork" :placeholder="$t('form.blockchain.blockchainMainNetwork.placeholder')
                  ">
                </a-input>
              </a-form-item>
            </a-col>
            <a-col :span="8">
              <a-form-item :label="$t('form.blockchain.blockchainNetworkType')" field="blockchainNetworkType">
                <a-select v-model="formData.blockchainNetworkType" :placeholder="$t('form.blockchain.blockchainNetworkType.placeholder')
                  ">
                  <a-option value="Layer 1">Layer 1</a-option>
                  <a-option value="Layer 2">Layer 2</a-option>
                </a-select>
              </a-form-item>
            </a-col>
          </a-row>
        </a-card>
        <a-card class="general-card" :bordered="false">
          <template #title>
            {{ $t('form.blockchain.remark') }}
          </template>
          <a-form-item field="audio.approvers">
            <a-textarea v-model="formData.remark" :placeholder="$t('form.blockchain.remark.placeholder')"
              style="height: 120px;" />
          </a-form-item>
        </a-card>
      </a-space>
      <div class="actions">
        <a-space>
          <a-button>
            {{ $t('groupForm.reset') }}
          </a-button>
          <a-button type="primary" :loading="loading" @click="onSubmitClick">
            {{ $t('groupForm.submit') }}
          </a-button>
        </a-space>
      </div>
    </a-form>
  </div>
</template>

<script lang="ts">
import { reactive, ref } from 'vue';
import { FormInstance } from '@arco-design/web-vue/es/form';
import { addBlockchain, Blockchain } from '@/api/basic';
import useLoading from '@/hooks/loading';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const router = useRouter();

    const formData = reactive({
      blockchainCode: '',
      blockchainName: '',
      blockchainMainNetwork: '',
      blockchainNetworkType: '',
      remark: ''
    });
    const { loading, setLoading } = useLoading();
    const post = async (
        blockchain: Blockchain = {
          blockchainCode: formData.blockchainCode,
          blockchainName: formData.blockchainName,
          blockchainMainNetwork: formData.blockchainMainNetwork,
          blockchainNetworkType: formData.blockchainNetworkType
        }
      ) => {
        setLoading(true);
        try {
          await addBlockchain(blockchain);
          // router.push('/basic/blockchain');
        } catch (err) {
          console.log(err);
        } finally {
          setLoading(false);
        }
      };
      const onSubmitClick = () =>{
        post();
      };
      return {
        formData,
        loading,
        onSubmitClick,
        name: 'BlockchainAdd'
      }
    }
}

</script>

<style scoped lang="less">
.container {
  padding: 0 20px 40px 20px;
  overflow: hidden;
}

.actions {
  position: fixed;
  left: 0;
  right: 0;
  bottom: 0;
  height: 60px;
  padding: 14px 20px 14px 0;
  background: var(--color-bg-2);
  text-align: right;
}
</style>
