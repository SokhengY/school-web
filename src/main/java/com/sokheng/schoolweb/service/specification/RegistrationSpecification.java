package com.sokheng.schoolweb.service.specification;

import com.sokheng.schoolweb.dto.registration_dto.RegistrationSearch;
import com.sokheng.schoolweb.entity.CustomerEntity;
import com.sokheng.schoolweb.entity.RegistrationEntity;
import com.sokheng.schoolweb.utils.DateTimeUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class RegistrationSpecification implements Specification<RegistrationEntity> {

    private RegistrationSearch search;

    @Override
    public Predicate toPredicate(Root<RegistrationEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();
        if (search.getCustomerId() != 0 && search.getTimestamp().trim().isEmpty()){
            predicates.add(criteriaBuilder.equal(root.get("customerEntity"), new CustomerEntity(search.getCustomerId())));
        }
        if (search.getCustomerId() != 0 && !search.getTimestamp().trim().isEmpty()){
            Timestamp timestamp = DateTimeUtil.stringToTimestamp(search.getTimestamp());
            predicates.add(criteriaBuilder.equal(root.get("customerEntity"), new CustomerEntity(search.getCustomerId())));
            predicates.add(criteriaBuilder.equal(root.get("createdAt"), timestamp));
        }
        if (search.getCustomerId() == 0 && !search.getTimestamp().trim().isEmpty()){
            Timestamp timestamp = DateTimeUtil.stringToTimestamp(search.getTimestamp());
            predicates.add(criteriaBuilder.equal(root.get("createdAt"), timestamp));
        }
        if (search.getCustomerId() == 0 && search.getTimestamp().trim().isEmpty()){
            predicates.add(criteriaBuilder.isNotNull(root.get("customerEntity")));
        }
        return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
    }
}
