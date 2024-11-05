package org.example.mangodash.use_case.data_processing.Extension;

import java.net.MalformedURLException;

public interface ExtensionInputBoundary {
    void executeSaveAPIData(ExtensionDataInput extensionDataInput) throws MalformedURLException;
}
