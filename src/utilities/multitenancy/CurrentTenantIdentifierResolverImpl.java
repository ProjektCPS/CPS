package utilities.multitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    public static ThreadLocal<String> _tenantIdentifier = new ThreadLocal<>();
    public static String DEFAULT_TENANT_ID = "sprava_cien_konto";

    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenantId = _tenantIdentifier.get();
        if (tenantId == null) {
            tenantId = DEFAULT_TENANT_ID;
        }

        System.out.println("ThreadLocal tenantId is: " + tenantId);
        return tenantId;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

}
