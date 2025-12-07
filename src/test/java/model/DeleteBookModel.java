package model;

import lombok.Data;

@Data
public class DeleteBookModel {

  private String userId;
  private String isbn;
  private String message;

}
