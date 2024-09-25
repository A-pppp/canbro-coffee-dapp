import { DEFAULT_LAYOUT } from '../base';
import { AppRouteRecordRaw } from '../types';

const BASE: AppRouteRecordRaw = {
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
      component: () => import('@/views/dashboard/workplace/index.vue'),
      meta: {
        locale: 'menu.basic.blockchain',
        requiresAuth: true,
        roles: ['*'],
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

export default BASE;