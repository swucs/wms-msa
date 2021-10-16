package com.sycoldstorage.customerservice.spec;

import com.sycoldstorage.customerservice.dto.CustomerSearchRequest;
import com.sycoldstorage.customerservice.entity.Customer;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 고객리스트 검색조건을 위한 Specification
 */
public class CustomerSpecification {

    public static Specification<Customer> searchWith(CustomerSearchRequest customerSearchRequest) {
        return (Specification<Customer>) ((root, query, builder) -> {

            List<Predicate> predicate = new ArrayList<>();

            if (StringUtils.isNotBlank(customerSearchRequest.getName())) {
                predicate.add(likeName(root, builder, customerSearchRequest.getName()));
            }

            if (customerSearchRequest.getId() != null) {
                predicate.add(equalId(root, builder, customerSearchRequest.getId()));
            }

            if (StringUtils.isNotBlank(customerSearchRequest.getUseYn())) {
                if (StringUtils.equals(customerSearchRequest.getUseYn(), "Y")) {
                    predicate.add(equalUse(root, builder, true));
                } else if (StringUtils.equals(customerSearchRequest.getUseYn(), "N")) {
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
