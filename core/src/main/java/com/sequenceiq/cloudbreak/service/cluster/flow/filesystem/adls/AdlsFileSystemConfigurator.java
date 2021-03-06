package com.sequenceiq.cloudbreak.service.cluster.flow.filesystem.adls;

import static com.sequenceiq.cloudbreak.api.model.FileSystemType.ADLS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sequenceiq.cloudbreak.api.model.AdlsFileSystemConfiguration;
import com.sequenceiq.cloudbreak.api.model.FileSystemType;
import com.sequenceiq.cloudbreak.domain.Credential;
import com.sequenceiq.cloudbreak.service.cluster.flow.blueprint.BlueprintConfigurationEntry;
import com.sequenceiq.cloudbreak.service.cluster.flow.filesystem.AbstractFileSystemConfigurator;
import com.sequenceiq.cloudbreak.service.cluster.flow.filesystem.FileSystemScriptConfig;

@Component
public class AdlsFileSystemConfigurator extends AbstractFileSystemConfigurator<AdlsFileSystemConfiguration> {

    @Override
    public List<BlueprintConfigurationEntry> getFsProperties(AdlsFileSystemConfiguration fsConfig, Map<String, String> resourceProperties) {
        List<BlueprintConfigurationEntry> bpConfigs = new ArrayList<>();
        String clientId = fsConfig.getClientId();
        String credential = fsConfig.getCredential();
        String tenantId = fsConfig.getTenantId();
        bpConfigs.add(new BlueprintConfigurationEntry("core-site", "dfs.adls.oauth2.access.token.provider.type", "ClientCredential"));
        bpConfigs.add(new BlueprintConfigurationEntry("core-site", "dfs.adls.oauth2.client.id", clientId));
        bpConfigs.add(new BlueprintConfigurationEntry("core-site", "dfs.adls.oauth2.credential", credential));
        bpConfigs.add(new BlueprintConfigurationEntry("core-site", "dfs.adls.oauth2.refresh.url",  "https://login.microsoftonline.com/"
                + tenantId + "/oauth2/token"));
        bpConfigs.add(new BlueprintConfigurationEntry("core-site", "fs.AbstractFileSystem.adl.impl", "org.apache.hadoop.fs.adl.Adl"));
        bpConfigs.add(new BlueprintConfigurationEntry("core-site", "fs.adl.impl", "org.apache.hadoop.fs.adl.AdlFileSystem"));
        bpConfigs.add(new BlueprintConfigurationEntry("core-site", "fs.adls.oauth2.resource", "https://management.core.windows.net/"));

        return bpConfigs;
    }

    @Override
    public String getDefaultFsValue(AdlsFileSystemConfiguration fsConfig) {
        return "adl://" + fsConfig.getAccountName() + ".azuredatalakestore.net";
    }

    @Override
    public FileSystemType getFileSystemType() {
        return ADLS;
    }

    @Override
    protected List<FileSystemScriptConfig> getScriptConfigs(Credential credential, AdlsFileSystemConfiguration fsConfig) {
        return new ArrayList<>();
    }

}
