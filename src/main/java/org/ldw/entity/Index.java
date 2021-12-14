package org.ldw.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Index {
    private String keyword;
    private List<String> bookCodeSet = new ArrayList<>();
}
