package com.jacob.micro.distributed.id.generator.biz.core;

import com.jacob.micro.distributed.id.generator.biz.core.common.Result;

public interface IDGen {
    Result get(String key);
    boolean init();
}
