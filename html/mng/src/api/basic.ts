import axios from 'axios';

export interface BlockchainQueryParams {
  blockchainCode?: string; 
  blockchainName?: string;
  blockchainState?: number; 
  page: number; 
  size: number;
}

export interface Blockchain{
  blockchainId: string,
  blockchainCode: string,
  blockchainName: string,
  blockchainMainNetwork: string,
  blockchainNetworkType: string,
  blockchainState: number,
  createBy: string,
  createTime: number,
  modifyBy: string,
  modifyTime: number,
  remark: string,

}

export interface RetVo{
  code: number;
  msg: string;
  data: Page;
}

export interface Page{
  total: number;
  size: number;
  current: number;
  records: Blockchain[];
}

export function queryBlockchainPage(blockchainRequest: BlockchainQueryParams) {
  return axios.post<RetVo>('/blockchain/page', blockchainRequest);
}
