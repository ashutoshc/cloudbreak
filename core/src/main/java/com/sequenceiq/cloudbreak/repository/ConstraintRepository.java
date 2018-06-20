package com.sequenceiq.cloudbreak.repository;

import javax.transaction.Transactional;

import com.sequenceiq.cloudbreak.aspect.DisabledBaseRepository;
import com.sequenceiq.cloudbreak.domain.Constraint;
import com.sequenceiq.cloudbreak.aspect.DisablePermission;
import com.sequenceiq.cloudbreak.service.EntityType;

@EntityType(entityClass = Constraint.class)
@Transactional(Transactional.TxType.REQUIRED)
@DisablePermission
public interface ConstraintRepository extends DisabledBaseRepository<Constraint, Long> {


}
