package com.ecom.utility;

import com.ecom.dto.CustomerDto;
import com.ecom.enums.SortDirection;

import java.util.Comparator;

public class CustomerSortUtility implements Comparator<CustomerDto> {

    private final SortDirection sortDirection;

    public CustomerSortUtility(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }

    @Override
    public int compare(CustomerDto dto1, CustomerDto dto2) {

        return sortDirection.equals(SortDirection.ASC)?
                dto1.purchaseDate().compareTo(dto2.purchaseDate()):
                dto2.purchaseDate().compareTo(dto1.purchaseDate());
    }
}
