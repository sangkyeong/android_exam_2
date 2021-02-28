package com.example.sbs.myapplication.api;

import com.example.sbs.myapplication.Pokemon;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokeApi__getPokemons__ResponseBody {
    private int count;
    private String next;
    private String previous;
    private List<Pokemon> results;
}
