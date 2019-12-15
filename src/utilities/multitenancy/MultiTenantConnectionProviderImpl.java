package utilities.multitenancy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

public class MultiTenantConnectionProviderImpl extends AbstractMultiTenantConnectionProvider {

    private static final long serialVersionUID = 730657320773645224L;

    private final String DB_PREFIX = "tenant_id_";

    private final HashMap<String, ConnectionProvider> connProviderMap = new HashMap<>();

    public MultiTenantConnectionProviderImpl() {

        final List<String> providerNames = new ArrayList<String>();
        providerNames.add("sprava_cien_konto");
        providerNames.add(DB_PREFIX + "1");
        providerNames.add(DB_PREFIX + "2");
        providerNames.add(DB_PREFIX + "3");

        for (final String providerName : providerNames) {
            this.connProviderMap.put(providerName, new ConnectionProviderImpl(providerName));
        }
    }

    @Override
    protected ConnectionProvider getAnyConnectionProvider() {
        return this.connProviderMap.get(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);
    }

    @Override
    protected ConnectionProvider selectConnectionProvider(String tenantId) {
        ConnectionProvider connectionProvider = this.connProviderMap.get(DB_PREFIX + tenantId);

        String tenantIdInfo = DB_PREFIX + tenantId;
        if (connectionProvider == null) {
            tenantIdInfo = CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID;
            connectionProvider = new ConnectionProviderImpl(CurrentTenantIdentifierResolverImpl.DEFAULT_TENANT_ID);
        }

        System.out.println("ThreadLocal tenantId is: " + tenantIdInfo);
        return connectionProvider;
    }
}
