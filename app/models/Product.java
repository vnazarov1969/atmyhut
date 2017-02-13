package models;

import java.io.File;
import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import com.avaje.ebean.PagedList;
import play.data.format.Formats;
import play.data.validation.*;

/**
 * Created by vnazarov on 05/02/17.
 */
  @Entity
  public class Product extends Model {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

//    @Constraints.Required
    private Long category_id = new Long(1);

//    @Constraints.Required
    private String name;


    @Formats.DateTime(pattern="yyyy-MM-dd")
    private Date introduced;

    @Formats.DateTime(pattern="yyyy-MM-dd")
    private Date discontinued;

    private String description;

  @Lob
  @Basic(fetch=FetchType.EAGER)
  private byte[] photo;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCategoryId() {
    return category_id;
  }

  public void setCategoryId(Long category_id) {
    this.category_id = category_id;
  }

  public Date getIntroduced() {
    return introduced;
  }

  public void setIntroduced(Date introduced) {
    this.introduced = introduced;
  }

  public Date getDiscontinued() {
    return discontinued;
  }

  public void setDiscontinued(Date discontinued) {
    this.discontinued = discontinued;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public byte[] getPhoto() {
    return photo;
  }

  public void setPhoto(byte[] photo) {
    this.photo = photo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
     * Generic query helper for entity Computer with id Long
     */
    public static Finder<Long,Product> find = new Finder<Long,Product>(Long.class, Product.class);

    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static PagedList<Product> page(int page, int pageSize, String sortBy, String order, String filter) {
      return
              find.where()
                      .ilike("name", "%" + filter + "%")
                      .orderBy(sortBy + " " + order)
                      .findPagedList(page, pageSize);
//                      .setFetchAhead(false)
//                      ..getPageList(page);
    }


  }
