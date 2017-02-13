package models;

import com.avaje.ebean.PagedList;
import com.avaje.ebean.RawSqlBuilder;
import play.data.format.Formats;
import play.data.validation.Constraints;
import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

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
  @JoinColumn(name="COMPANY_ID")
  public Company company;

  @ManyToOne
  @JoinColumn(name="PRODUCT_ID")
  public Product product;

  public float price;

  /**
   * Generic query helper for entity Computer with id Long
   */
  public static Finder<Long, Price> find = new Finder<Long, Price>(Long.class, Price.class);

  /**
   * Return a page of computer
   *
   * @param page     Page to display
   * @param pageSize Number of computers per page
   * @param sortBy   Computer property used for sorting
   * @param order    Sort order (either or asc or desc)
   * @param filter   Filter applied on the name column
   */
  public static PagedList<Price> page(int page, int pageSize, String sortBy, String order, String filter) {
    return
            find
                    .orderBy("introduced asc")
//                    .fetch("company")
                    .fetch("product")
                    .findPagedList(page, pageSize);
//                    .findPagingList(pageSize)
//                    .setFetchAhead(false)
//                    .getPage(page);
  }

  public static PagedList<Price> pageActual(int page, int pageSize, String sortBy, String order, String filter) {
    List<Object> list = find
            .setRawSql(RawSqlBuilder
//              .parse("select MAX(price.id) from price group by product_id, company_id ")
//              .columnMapping("MAX(price.id)","id")
                .parse("select id from price p join (select max(price.introduced) as max_introduced, product_id, company_id " +
                        "from price group by product_id, company_id) p1 " +
                        "on p.introduced = p1.max_introduced and p.product_id = p1.product_id and p.company_id = p1.company_id")
                .columnMapping("id","id")
                .create())
                .findIds();
    return

            find.where().idIn(list)
                    .orderBy("introduced asc")
//                    .fetch("company")
                    .fetch("product")
                    .findPagedList(page,pageSize);
//                    .findPagingList(pageSize)
//                    .setFetchAhead(false)
//                    .getPage(page);
  }


}

