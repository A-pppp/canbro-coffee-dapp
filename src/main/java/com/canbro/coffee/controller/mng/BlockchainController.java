package com.canbro.coffee.controller.mng;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.canbro.coffee.controller.mng.vo.BlockchainRequest;
import com.canbro.coffee.pojo.entity.Blockchain;
import com.canbro.coffee.pojo.vo.RetVo;
import com.canbro.coffee.service.IBlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  后台区块链模块
 * </p>
 *
 * @author A-pppp
 * @since 2024-09-24
 */
@RestController
@RequestMapping("/mng/blockchain")
public class BlockchainController {

    @Autowired
    private IBlockchainService blockchainService;

    @PostMapping("/page")
    public RetVo page(@RequestBody BlockchainRequest blockchainRequest) {

        if (blockchainRequest==null){
            return RetVo.failed();
        }

        Page<Blockchain> result = blockchainService.page(blockchainRequest);
        return RetVo.success(result);
    }

    @PostMapping("/add")
    public RetVo add(@RequestBody Blockchain blockchain) {
        boolean result = blockchainService.add(blockchain);
        if (result){
            return RetVo.success();
        }else {
            return RetVo.failed();
        }
    }
}
