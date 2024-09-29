import { DEFAULT_LAYOUT } from '../base';
import { AppRouteRecordRaw } from '../types';

const BASIC: AppRouteRecordRaw = {
  path: '/basic',
  name: 'basic',
  component: DEFAULT_LAYOUT,
  meta: {
    locale: 'menu.basic',
    requiresAuth: true,
    icon: 'icon-mosaic',
    order: 1,
  },
  children: [
    {
      path: 'blockchain',
      name: 'Blockchain',
      component: () => import('@/views/basic/blockchain/index.vue'),
      meta: {
        locale: 'menu.basic.blockchain',
        requiresAuth: true,
        roles: ['*'],
        hideInMenu: false
      },
        
    },
    {
      path: 'blockchain/add',
      name: 'BlockchainAdd',
      component: () => import('@/views/basic/blockchain/add.vue'),
      meta: {
        locale: 'menu.basic.blockchain.add',
        requiresAuth: true,
        roles: ['*'],
        hideInMenu: true
      },
    },
    {
      path: `blockchain/detail/:blockchainId`,
      name: 'BlockchainDetail',
      component: () => import('@/views/basic/blockchain/detail.vue'),
      meta: {
        locale: 'menu.basic.blockchain.detail',
        requiresAuth: true,
        roles: ['*'],
        hideInMenu: true
      },
    },
    {
      path: 'wallet',
      name: 'Wallet',
      component: () => import('@/views/dashboard/monitor/index.vue'),
      meta: {
        locale: 'menu.basic.wallet',
        requiresAuth: true,
        roles: ['admin'],
      },
    },
    
  ],
};

export default BASIC;