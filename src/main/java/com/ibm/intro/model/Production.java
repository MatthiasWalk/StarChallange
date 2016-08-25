package com.ibm.intro.model;

/**
 * The production represents the business object.
 * 
 * @author Richard Holzeis
 */
public class Production extends AbstractObject {

	/** the type of the production */
	private ProductionType productionType;
	
	/** the sequence of the production */
	private Long sequence;
	
	/** the status of the production */
	private Status status;
	
	/**
	 * Instantiates a default production
	 * 
	 * @param potype: the production type
	 * @param sequence: the sequence
	 */
	public Production(ProductionType potype, Long sequence) {
		super();
		this.productionType = potype;
		this.sequence = sequence;
		this.status = Status.CREATED;
	}
	
	/**
	 * Instantiates a clone of the given source production.
	 * 
	 * @param source: the source object to be cloned.
	 */
	private Production(Production source) {
		super(source);
		this.productionType = source.getProductionType();
		this.sequence = source.getSequence();
		this.status = source.getStatus();
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status.
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status: the status.
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * Gets the sequence.
	 * 
	 * @return the sequence.
	 */
	public Long getSequence() {
		return sequence;
	}

	/**
	 * Sets the sequence.
	 * 
	 * @param sequence: the sequence.
	 */
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	/**
	 * Gets the production type.
	 * 
	 * @return the production type.
	 */
	public ProductionType getProductionType() {
		return productionType;
	}

	/**
	 * Sets the production type.
	 * 
	 * @param productionType: the production type.
	 */
	public void setProductionType(ProductionType productionType) {
		this.productionType = productionType;
	}
	
	/**
	 * Clones the object.
	 */
	@Override
	public Object clone() {
		return new Production(this);
	}
}
