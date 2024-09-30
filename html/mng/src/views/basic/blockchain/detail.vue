<template>
    <div class="container">
      <Breadcrumb :items="['menu.basic', 'menu.basic.blockchain', 'menu.basic.blockchain.detail']" />
      <a-space direction="vertical" :size="16" fill>
        <a-card class="general-card">
          <a-space :size="16" direction="vertical" fill>
            <a-descriptions style="margin-top: 20px" :data="renderData" size="large" title="基本信息" :column="1">
            </a-descriptions>
          </a-space>
          <a-button  @click="goBack">
            {{ $t('groupForm.back') }}
          </a-button>
        </a-card>
      </a-space>
    </div>
  </template>
  
  <script lang="ts" >
    import { ref } from 'vue';
    import useLoading from '@/hooks/loading';
    import { queryBlockchain } from '@/api/basic';
    import { useRoute } from 'vue-router';  
    import { DescData } from '@arco-design/web-vue/es/descriptions/interface';
    import { useI18n } from 'vue-i18n';
    import { parseDateTime } from "@/utils/date-formart";
    import router from '@/router';

    export default {
      name: 'Basic',
      setup(){
        const { setLoading } = useLoading(true);
        const renderData = ref<DescData[]>([]);
        const route = useRoute();
        const { t } = useI18n();
        const blockchainId = parseInt(route.params.blockchainId as string);
        const fetchCurrentData = async () => {
          try {
            const { data } = await queryBlockchain(blockchainId);
            for(const key in data){
              let value = data[key];
              if(key=='blockchainState'){
                if(value==0){
                  value = t('table.blockchain.blockchainState.create')
                }else if(value==1){
                  value = t('table.blockchain.blockchainState.enable')
                }else if(value==2){
                  value = t('table.blockchain.blockchainState.disable')
                }
              }else if(key=='createTime'||key=='modifyTime'){
                value = parseDateTime(value);
              }

              renderData.value.push({
                label: t('table.blockchain.'+key), 
                // label: key,
                value: value
              })
            }
          } catch (err) {
            console.log(err);
          } finally {
            setLoading(false);
          }
        };
        fetchCurrentData();
        const goBack = () => {
          router.go(-1);
        }
        return{
          renderData,
          blockchainId,
          goBack
        } 
    },
    
  };
  </script>
  
  <style scoped lang="less">
    .container {
      padding: 0 20px 20px 20px;
    }
  
    .steps {
      max-width: 548px;
      margin: 0 auto 10px;
    }
  </style>
  