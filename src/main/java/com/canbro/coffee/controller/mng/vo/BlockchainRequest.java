package com.canbro.coffee.controller.mng.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockchainRequest {

    private String blockchainCode;

    private String blockchainName;

    private Integer blockchainState;

    private int page;

    private int size;
}
