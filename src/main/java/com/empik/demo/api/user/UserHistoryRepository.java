package com.empik.demo.api.user;

import com.empik.demo.api.user.model.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long>, QueryByExampleExecutor<UserHistory> {

}
