package info.maalvarez.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.transaction.Transactional;

import info.maalvarez.domain.ProductExampleEntity;

@Named
public class ProductExampleService extends BaseService<ProductExampleEntity> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public ProductExampleService(){
        super(ProductExampleEntity.class);
    }
    
    @Transactional
    public List<ProductExampleEntity> findAllProductExampleEntities() {
        
        return entityManager.createQuery("SELECT o FROM ProductExample o ", ProductExampleEntity.class).getResultList();
    }
    
    @Override
    @Transactional
    public long countAllEntries() {
        return entityManager.createQuery("SELECT COUNT(o) FROM ProductExample o", Long.class).getSingleResult();
    }
    
    @Override
    protected void handleDependenciesBeforeDelete(ProductExampleEntity productExample) {

        /* This is called before a ProductExample is deleted. Place here all the
           steps to cut dependencies to other entities */
        
    }

}
