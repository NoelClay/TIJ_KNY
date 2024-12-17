package network.practice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product { //데이터 형식
private int no;
private String name;
private int price;
private int stock;
}
