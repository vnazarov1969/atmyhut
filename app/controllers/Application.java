package controllers;
import com.avaje.ebean.PagedList;
import org.postgresql.largeobject.BlobOutputStream;
import play.Play;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.Logger;



import views.html.*;

import models.*;

import java.io.*;

/**
 * Manage a database of computers
 */
public class Application extends Controller {

  // buffer size used for reading and writing
  private static final int BUFFER_SIZE = 8192;

  /**
   * Reads all bytes from an input stream and writes them to an output stream.
   */
  private static long copy(InputStream source, OutputStream sink)
          throws IOException
  {
    long nread = 0L;
    byte[] buf = new byte[BUFFER_SIZE];
    int n;
    while ((n = source.read(buf)) > 0) {
      sink.write(buf, 0, n);
      nread += n;
    }
    return nread;
  }

    /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(
            routes.Application.list(0, "name", "asc", "")
    );

    /**
     * Handle default path requests, redirect to computers list
     */
//    public static Result index() {
//        return ok(index.render());
//    }

  /**
   * Display the paginated list of computers.
   *
   * @param page Current page number (starts from 0)
   * @param sortBy Column to be sorted
   * @param order Sort order (either asc or desc)
   * @param filter Filter applied on computer names
   */
  public static Result index(int page, String sortBy, String order, String filter) {
    PagedList<Price> pricePagedList = Price.pageActual(page, 10, sortBy, order, filter);

    for (Price price : pricePagedList.getList()){
      Logger.debug("Price toString. {}", String.valueOf(price));
    }

    return ok(
            index.render(
                    Price.pageActual(page, 20, sortBy, order, filter),
//                    Product.page(page, 20, sortBy, order, filter),
                    sortBy, order, filter
            )
    );
  }

  public static Result productPhoto(long productId) {
    Product product = Product.find.byId(productId);

      if (product == null) {
        return notFound();

      }

      if (product.getPhoto() == null) {
        return ok(Play.application().getFile("/public/images/178260.jpg"), true).as("image/jpeg");
      }

      return ok(product.getPhoto()).as("image/jpeg");
  }


  public static Result uploadProductPhoto(long productId) {
    Form<Product> productForm = form(Product.class).bindFromRequest();
    Http.MultipartFormData body = request().body().asMultipartFormData();
    if (body == null){
      return status(500, "Invalid form body!!!");
    }

    Http.MultipartFormData.FilePart uploadFilePart = body.getFile("photo");
    if (uploadFilePart == null){
      return status(500, "ERROR: Invalid file!!!");
    }

    try {
      Product product = productForm.get();
//      productForm.get().update();

//      Product product = Product.find.byId(productId);
      InputStream in = null;
      ByteArrayOutputStream out = null;

      try {
        in = new FileInputStream((File) uploadFilePart.getFile());
        out = new ByteArrayOutputStream();
        copy(in,out);
        product.setPhoto(out.toByteArray());
        if (product.getId()!=null) {
          product.update();
        }else{
          product.save();
        }
      }finally {
//        if (out != null) out.close();
//        if (in != null) in.close();
      }
    } catch (Throwable e) {
      return status(500, "ERROR: storing!!!");
    }

    return ok("File uploaded!!!");
  }



  /**
     * Display the paginated list of computers.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on computer names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Computer.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Computer.
     *
     * @param id Id of the computer to edit
     */
    public static Result edit(Long id) {
        Form<Computer> computerForm = form(Computer.class).fill(
            Computer.find.byId(id)
        );
        return ok(
            editForm.render(id, computerForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the computer to edit
     */
    public static Result update(Long id) {
        Form<Computer> computerForm = form(Computer.class).bindFromRequest();
        if(computerForm.hasErrors()) {
            return badRequest(editForm.render(id, computerForm));
        }
        computerForm.get().update();
        flash("success", "Computer " + computerForm.get().name + " has been updated");
        return GO_HOME;
    }
    
    /**
     * Display the 'new computer form'.
     */
    public static Result create() {
        Form<Computer> computerForm = form(Computer.class);
        return ok(
            createForm.render(computerForm)
        );
    }
    
    /**
     * Handle the 'new computer form' submission 
     */
    public static Result save() {
        Form<Computer> computerForm = form(Computer.class).bindFromRequest();
        if(computerForm.hasErrors()) {
            return badRequest(createForm.render(computerForm));
        }
        computerForm.get().save();
        flash("success", "Computer " + computerForm.get().name + " has been created");
        return GO_HOME;
    }
    
    /**
     * Handle computer deletion
     */
    public static Result delete(Long id) {
        Computer.find.ref(id).delete();
        flash("success", "Computer has been deleted");
        return GO_HOME;
    }
    

}
            
