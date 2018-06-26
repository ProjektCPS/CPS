package API;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("webapi")
public class Aplication extends Application {

    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();

        // Annotated @Path endpoint
        s.add(filters.SecurityFilter.class);
        s.add(SecuredResource.class);
        return s;
    }
}
