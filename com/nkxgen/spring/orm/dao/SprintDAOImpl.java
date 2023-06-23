package com.nkxgen.spring.orm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nkxgen.spring.orm.model.FunctionalUnit;
import com.nkxgen.spring.orm.model.ModuleDTO;
import com.nkxgen.spring.orm.model.Sprint;
import com.nkxgen.spring.orm.model.SprintResource;
import com.nkxgen.spring.orm.model.SprintTasks;
import com.nkxgen.spring.orm.model.Task;
import com.nkxgen.spring.orm.model.User;

@Repository
@Transactional
public class SprintDAOImpl implements SprintDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Sprint> getBaskLogs() {
		String query = "SELECT s FROM Sprint s WHERE EXISTS (SELECT 1 FROM Task t WHERE t.module.id = s.moduleId.id AND t.taskCompletedDateTime IS NULL)";
		return entityManager.createQuery(query, Sprint.class).getResultList();
	}

	@Override
	public Sprint getSprintDetails(int sprintId) {
		return entityManager.find(Sprint.class, sprintId);
	}

	@Override
	public List<Task> getTasks(int modlId) {
		String query = "SELECT t FROM Task t WHERE t.module.id = :modlId";

		return entityManager.createQuery(query, Task.class).setParameter("modlId", modlId).getResultList();
	}

	@Override
	public List<Sprint> getAllSprints() {
		String query = "SELECT s FROM Sprint s";
		return entityManager.createQuery(query, Sprint.class).getResultList();
	}

	@Override
	public List<SprintTasks> allTaskBySprintId(Integer sprintId) {
		String query = "SELECT st FROM SprintTasks st WHERE st.id.sprnId = :sprintId";

		return entityManager.createQuery(query, SprintTasks.class).setParameter("sprintId", sprintId).getResultList();
	}

	@Override
	public void storeSprint(Sprint sprint) {
		if (sprint.getSprintId() == 0) {
			entityManager.persist(sprint); // New entity, use persist
		} else {
			entityManager.merge(sprint); // Existing entity, use merge
		}
	}

	@Override
	public List<ModuleDTO> getSprintModulesByProjectId(int projectId) {
		String query = "SELECT new com.nkxgen.spring.orm.model.ModuleDTO(m.id, m.name, m.description, m.project.projectId) FROM Module m WHERE m.project.projectId = :projectId AND m.id NOT IN (SELECT s.moduleId.id FROM Sprint s)";
		TypedQuery<ModuleDTO> typedQuery = entityManager.createQuery(query, ModuleDTO.class);
		typedQuery.setParameter("projectId", projectId);
		List<ModuleDTO> moduleDTOList = typedQuery.getResultList();
		return moduleDTOList;
	}

	@Override
	public List<FunctionalUnit> getFunctionalUnitsByModId(int modl_id) {
		String query = "SELECT distinct fu FROM FunctionalUnit fu WHERE fu.id.module.id = :modlId";

		return entityManager.createQuery(query, FunctionalUnit.class).setParameter("modlId", modl_id).getResultList();
	}

	public void storeTask(Task task) {
		if (task.getTaskId() == 0) {
			entityManager.persist(task); // New entity, use persist
		} else {
			entityManager.merge(task); // Existing entity, use merge
		}
	}

	public List<User> getAllUsers() {
		String query = "SELECT u FROM User u";
		return entityManager.createQuery(query, User.class).getResultList();
	}
   public void storeSprintResource(SprintResource src)
   {
	   entityManager.persist(src);
   }
}