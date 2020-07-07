package com.cinder.question;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChainTest {
    private int id;
    private String name;

    public static void main(String[] args) {
        ChainTest chainTest = new ChainTest();
        chainTest.setId(1).setName("a");
    }
}
