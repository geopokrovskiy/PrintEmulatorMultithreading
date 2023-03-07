package com.geopokrovskiy.factory;

import com.geopokrovskiy.model.AbstractDocument;

public interface DocumentFactory {
   AbstractDocument newInstance();
}
