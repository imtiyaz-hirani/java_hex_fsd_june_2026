package com.ecom.utility;

import com.ecom.dto.ProductDto;
import com.ecom.enums.SortDirection;

import java.util.Comparator;

public class ProductSortUtility implements Comparator<ProductDto> {
    // if u r initializing instance variable in a constructor , make it final

    private final SortDirection sortDirection; // instance variable - scope of this goes in compare method too

    public ProductSortUtility(SortDirection sortDirection) { // this is a local variable having same name of instance variable
        this.sortDirection = sortDirection;
    }

    @Override
    public int compare(ProductDto dto1, ProductDto dto2) {
//        // [230 , 180]
//        if(dto1.price() > dto2.price()) {
//            return 1; // return a +ve number to swap
//        }
//        // [450 , 580]
//        if(dto1.price() < dto2.price()) {
//            return -1; // return a -ve number to do thing
//        }
//
//        return 0; // it considers both the prices as equal
//        if(sortDirection.equals(SortDirection.ASC))
//            return (int) (dto1.price() - dto2.price());
//
//        return  (int) (dto2.price() - dto1.price());

        return (int) (sortDirection.equals(SortDirection.ASC)?
                dto1.price() - dto2.price() :
                dto2.price() - dto1.price()) ;
    }
}
