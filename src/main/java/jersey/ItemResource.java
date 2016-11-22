package jersey;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bryan on 2-11-2016.
 */
@Path("/items")
public class ItemResource {
    public List<Item> items = new ArrayList<Item>() {{
        add(new Item("1", "Auto", "Audi"));
        add(new Item("2", "Auto", "BMW"));
        add(new Item("3", "Auto", "Ford"));
    }};

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTextItems() {
        String itemsInString = "";
        for (int i = 0; i < items.size(); i++) {
            itemsInString = itemsInString + items.get(i).toString() + "\n";
        }
        return itemsInString;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getJsonItems() {
        return items;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{sku}")
    public Item getJsonItem(@PathParam("sku") String sku) {
        System.out.println("sku: " + sku);

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getSku().equals(sku)) {
                return items.get(i);
            }
        }
        System.out.println("Comparing sku failed");
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public void addItem(Item item) {
        System.out.println("jersey.Item saved : " + item.toString());
        items.add(item);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public void deleteItem(Item item) {
        System.out.println("jersey.Item deleted : " + item.toString());

    }

}
