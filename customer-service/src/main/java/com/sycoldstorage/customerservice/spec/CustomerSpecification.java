package com.sycoldstorage.customerservice.spec;

import com.sycoldstorage.customerservice.dto.SearchCustomerRequest;
import com.sycoldstorage.customerservice.entity.Customer;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 고객리스트 검색조건을 위한 Specification
 */
public class CustomerSpecification {

    public static Specification<Customer> searchWith(SearchCustomerRequest searchCustomerRequest) {
        return (Specification<Customer>) ((root, query, builder) -> {

            List<Predicate> predicate = new ArrayList<>();

            if (StringUtils.isNotBlank(searchCustomerRequest.getName())) {
                predicate.add(likeName(root, builder, searchCustomerRequest.getName()));
            }

            if (searchCustomerRequest.getId() != null) {
                predicate.add(equalId(root, builder, searchCustomerRequest.getId()));
            }

            if (StringUtils.isNotBlank(searchCustomerRequest.getUseYn())) {
                if (StringUtils.equals(searchCustomerRequest.getUseYn(), "Y")) {
                    predicate.add(equalUse(root, builder, true));
                } else if (StringUtils.equals(searchCustomerRequest.getUseYn(), "N")) {
                    predicate.add(equalUse(root, builder, false));
                }
            }

            return builder.and(predicate.toArray(new Predicate[0]));
        });
    }


    private static Predicate likeName(Root<Customer> root, CriteriaBuilder criteriaBuilder, String name) {
        return criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    private static Predicate equalId(Root<Customer> root, CriteriaBuilder criteriaBuilder, Integer id) {
        return criteriaBuilder.equal(root.get("id"), id);
    }

    private static Predicate equalUse(Root<Customer> root, CriteriaBuilder criteriaBuilder, Boolean use) {
        return criteriaBuilder.equal(root.get("use"), use);
    }
}
