package API;

import com.google.gson.Gson;
import config.UsefulData;
import entities.KategorieEntity;
import entities.PredmetPredajaEntity;
import services.BaseService;
import services.BaseServiceImplement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("secured")
public class SecuredResource {

    @GET
    @Path("productTypes")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProductTypes() {
        BaseService baseService = new BaseServiceImplement();
        List<String> productItems = baseService.getProductType(UsefulData.ID_TENANT);
        String json = new Gson().toJson(productItems);
        return json;
    }

    @GET
    @Path("productCategories")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProductCategories(@QueryParam("productTypeName") String productTypeName) {
        BaseService baseService = new BaseServiceImplement();
        List<KategorieEntity> listCategoriesObjects = baseService.getProductCategories(UsefulData.ID_ADMIN,productTypeName);
        String json = new Gson().toJson(listCategoriesObjects);
        return json;
    }

    @GET
    @Path("products")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProducts(@QueryParam("productCategoryName") String productTypeName) {
        BaseService baseService = new BaseServiceImplement();
        List<PredmetPredajaEntity> productsItems = baseService.getProduct(UsefulData.ID_ADMIN,productTypeName);
        String json = new Gson().toJson(productsItems);
        return json;
    }
}

