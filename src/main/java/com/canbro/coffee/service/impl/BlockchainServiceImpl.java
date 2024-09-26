package com.canbro.coffee.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canbro.coffee.controller.mng.vo.BlockchainRequest;
import com.canbro.coffee.pojo.entity.Blockchain;
import com.canbro.coffee.mapper.BlockchainMapper;
import com.canbro.coffee.service.IBlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author A-pppp
 * @since 2024-09-24
 */
@Service
public class BlockchainServiceImpl implements IBlockchainService {

    @Autowired
    private BlockchainMapper blockchainMapper;

    @Override
    public Page<Blockchain> page(BlockchainRequest blockchainRequest) {

        String blockchainCode = blockchainRequest.getBlockchainCode();
        String blockchainName = blockchainRequest.getBlockchainName();
        Integer blockchainState = blockchainRequest.getBlockchainState();
        int page = blockchainRequest.getPage();
        int size = blockchainRequest.getSize();
        // 构建查询条件
        QueryWrapper<Blockchain> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(blockchainCode)){
            queryWrapper.eq("blockchain_code", blockchainCode);
        }
        if (StrUtil.isNotBlank(blockchainName)){
            queryWrapper.eq("blockchain_name", blockchainName);
        }
        if (!Objects.isNull(blockchainState)){
            queryWrapper.eq("blockchain_state", blockchainState);
        }

        Page<Blockchain> blockchainPage = new Page<>(page, size);

        return blockchainMapper.selectPage(blockchainPage, queryWrapper);
    }

    @Override
    public boolean add(Blockchain blockchain) {

        blockchain.setCreateTime(Long.parseLong(DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN)));

        int i = blockchainMapper.insert(blockchain);

        return i>0;
    }
}
