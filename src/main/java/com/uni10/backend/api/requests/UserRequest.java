package com.uni10.backend.api.requests;

import com.uni10.backend.api.filter.Filter;
import com.uni10.backend.entity.User;
import com.uni10.backend.specifications.Specifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class UserRequest implements Filter<User> {

    private List<String> username= new ArrayList<>();
    private List<String> role = new ArrayList<>();

    @Override
    public Specification<User> toSpecification() {
        Set<Specification<User>> specifications = new HashSet<>();
        specifications.add(toSpecification("username", username));
        specifications.add(toSpecification("role", role));
        return Specifications.and(specifications);
    }
}
