package filters;

import config.UsefulData;
import entities.UcetEntity;
import org.glassfish.jersey.internal.util.Base64;
import services.BaseService;
import services.BaseServiceImplement;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic";
    private static final String SECURED_URL_PREFIX = "secured";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
            List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
            if (authHeader != null && authHeader.size() > 0) {
                String authToken = authHeader.get(0);
                authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, " ").trim();
                String decodedString = Base64.decodeAsString(authToken);
                StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();

                BaseService loginService = new BaseServiceImplement();
                UcetEntity user = loginService.login(username, password);
                if (user != null) {
                    UsefulData.ID_ADMIN = user.getIdAdmin();
                    return;
                }
            }
            Response unauthorizedStatus = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("Užívateľ nema právo pristúpiť k dátam. Skotrolujte meno a heslo")
                    .build();

            requestContext.abortWith(unauthorizedStatus); }
    }
}
