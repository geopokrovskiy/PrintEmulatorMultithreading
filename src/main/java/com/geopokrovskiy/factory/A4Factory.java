package com.geopokrovskiy.factory;

import com.geopokrovskiy.model.A4;
import com.geopokrovskiy.model.AbstractDocument;

public class A4Factory implements DocumentFactory{
    @Override
    public AbstractDocument newInstance() {
        return new A4();
    }
}
