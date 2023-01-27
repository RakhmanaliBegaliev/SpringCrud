package com.peaksoft.dao.impl;

import com.peaksoft.dao.CompanyDao;
import com.peaksoft.entity.Company;
import com.peaksoft.entity.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Company> getAllCompany() {
    return entityManager.createQuery("from Company ").getResultList();
    }

    @Override
    public void addCompany(Company company) {
    entityManager.persist(company);
    }

    @Override
    public void updateCompany(Long id, Company company) {
    Company company1 = entityManager.find(Company.class, id);
    company1.setCompanyName(company.getCompanyName());
    company1.setLocatedCountry(company.getLocatedCountry());
    entityManager.merge(company1);
    }

    @Override
    public Company getById(Long id) {
        return entityManager.find(Company.class, id);
    }

    @Override
    public void deleteCompany(Company company) {
    entityManager.remove(company);
    }

    @Override
    public List<Course> getCourseByCompanyId(Long id) {
        List<Course>courses = entityManager.createQuery("from Course c join c.company com where com.id=?1")
                .setParameter(1, id).getResultList();
        return courses;
    }
}
