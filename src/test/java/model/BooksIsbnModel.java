package model;

import java.util.List;
import lombok.Data;

@Data
public class BooksIsbnModel {

  private List<IsbnModel> books;
}
