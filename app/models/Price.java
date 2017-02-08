package models;

import com.avaje.ebean.Page;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Currency;
import java.util.Date;

/**
 * Created by vnazarov on 05/02/17.
 */
@Entity
public class Price extends Model {

  private static final long serialVersionUID = 1L;

  @Id
  public Long id;

  @Constraints.Required
  @Formats.DateTime(pattern = "yyyy-MM-dd")
  public Date introduced;

  @Formats.DateTime(pattern = "yyyy-MM-dd")
  public Date discontinued;

  @ManyToOne
  public Company company;

  @ManyToOne
  public Product product;

  public Currency price;

  /**
   * Generic query helper for entity Computer with id Long
   */
  public static Finder<Long, Computer> find = new Finder<Long, Computer>(Long.class, Computer.class);

  /**
   * Return a page of computer
   *
   * @param page     Page to display
   * @param pageSize Number of computers per page
   * @param sortBy   Computer property used for sorting
   * @param order    Sort order (either or asc or desc)
   * @param filter   Filter applied on the name column
   */
  public static Page<Computer> page(int page, int pageSize, String sortBy, String order, String filter) {
    return
            find.where()
                    .ilike("name", "%" + filter + "%")
                    .orderBy(sortBy + " " + order)
                    .fetch("company")
                    .findPagingList(pageSize)
                    .setFetchAhead(false)
                    .getPage(page);
  }
}

