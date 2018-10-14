package info.maalvarez.web;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;

import info.maalvarez.domain.ProductExampleEntity;
import info.maalvarez.service.ProductExampleService;
import info.maalvarez.web.util.MessageFactory;

@Named("productExampleBean")
@ViewScoped
public class ProductExampleBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(ProductExampleBean.class.getName());
    
    private List<ProductExampleEntity> productExampleList;

    private ProductExampleEntity productExample;
    
    @Inject
    private ProductExampleService productExampleService;
    
    public void prepareNewProductExample() {
        reset();
        this.productExample = new ProductExampleEntity();
        // set any default values now, if you need
        // Example: this.productExample.setAnything("test");
    }

    public String persist() {

        String message;
        
        try {
            
            if (productExample.getId() != null) {
                productExample = productExampleService.update(productExample);
                message = "message_successfully_updated";
            } else {
                productExample = productExampleService.save(productExample);
                message = "message_successfully_created";
            }
        } catch (OptimisticLockException e) {
            logger.log(Level.SEVERE, "Error occured", e);
            message = "message_optimistic_locking_exception";
            // Set validationFailed to keep the dialog open
            FacesContext.getCurrentInstance().validationFailed();
        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "Error occured", e);
            message = "message_save_exception";
            // Set validationFailed to keep the dialog open
            FacesContext.getCurrentInstance().validationFailed();
        }
        
        productExampleList = null;

        FacesMessage facesMessage = MessageFactory.getMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        
        return null;
    }
    
    public String delete() {
        
        String message;
        
        try {
            productExampleService.delete(productExample);
            message = "message_successfully_deleted";
            reset();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occured", e);
            message = "message_delete_exception";
            // Set validationFailed to keep the dialog open
            FacesContext.getCurrentInstance().validationFailed();
        }
        FacesContext.getCurrentInstance().addMessage(null, MessageFactory.getMessage(message));
        
        return null;
    }
    
    public void onDialogOpen(ProductExampleEntity productExample) {
        reset();
        this.productExample = productExample;
    }
    
    public void reset() {
        productExample = null;
        productExampleList = null;
        
    }

    public ProductExampleEntity getProductExample() {
        if (this.productExample == null) {
            prepareNewProductExample();
        }
        return this.productExample;
    }
    
    public void setProductExample(ProductExampleEntity productExample) {
        this.productExample = productExample;
    }
    
    public List<ProductExampleEntity> getProductExampleList() {
        if (productExampleList == null) {
            productExampleList = productExampleService.findAllProductExampleEntities();
        }
        return productExampleList;
    }

    public void setProductExampleList(List<ProductExampleEntity> productExampleList) {
        this.productExampleList = productExampleList;
    }
    
}
