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
        return ((root, query, builder) -> {

            List<Predicate> predicate = new ArrayList<>();
            //기본적으로 deleted = false 인 데이터만 조회
            predicate.add(notDeleted(root, builder));

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

    /**
     * 기본적으로 deleted = false 인 데이터만 조회
     * @param root
     * @param criteriaBuilder
     * @return
     */
    private static Predicate notDeleted(Root<Customer> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("deleted"), false);
    }

    /**
     * name 컬럼 like 검색
     * @param root
     * @param criteriaBuilder
     * @param name
     * @return
     */
    private static Predicate likeName(Root<Customer> root, CriteriaBuilder criteriaBuilder, String name) {
        return criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    /**
     * id 컬럼 equal 검색
     * @param root
     * @param criteriaBuilder
     * @param id
     * @return
     */
    private static Predicate equalId(Root<Customer> root, CriteriaBuilder criteriaBuilder, Integer id) {
        return criteriaBuilder.equal(root.get("id"), id);
    }

    /**
     * use 컬럼 equal 검색
     * @param root
     * @param criteriaBuilder
     * @param use
     * @return
     */
    private static Predicate equalUse(Root<Customer> root, CriteriaBuilder criteriaBuilder, Boolean use) {
        return criteriaBuilder.equal(root.get("use"), use);
    }
}
