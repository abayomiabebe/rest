/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicexample.web.controller;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.basicexample.Item;
import org.openmrs.module.basicexample.api.BasicexampleService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Resource(name = RestConstants.VERSION_1 + "/item", supportedClass = Item.class, supportedOpenmrsVersions = { "1.8.*",
        "2.0.*", "2.1.*", "2.2.*", "2.3.*", "2.4.* " })
public class ItemResource extends DataDelegatingCrudResource<Item> {
	
	private static final Logger log = LoggerFactory.getLogger(ItemResource.class);
	
	@Override
	public Item getByUniqueId(String string) {
		List<Item> its = Context.getService(BasicexampleService.class).getAllItems();
		Item it = new Item();
		return it;
	}
	
	@Override
	public NeedsPaging<Item> doGetAll(RequestContext context) {
		return new NeedsPaging<Item>(Context.getService(BasicexampleService.class).getAllItems(), context);
	}
	
	@Override
	protected void delete(Item t, String string, RequestContext rc) throws ResponseException {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
		                                                               // Tools | Templates.
	}
	
	@Override
	public void purge(Item t, RequestContext rc) throws ResponseException {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
		                                                               // Tools | Templates.
	}
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation r) {
		if (r instanceof DefaultRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("description");
			description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
			description.addSelfLink();
			return description;
		} else if (r instanceof FullRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("description");
			description.addSelfLink();
			return description;
		}
		return null;
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addRequiredProperty("description");
		return description;
	}
	
	@Override
	public DelegatingResourceDescription getUpdatableProperties() throws ResourceDoesNotSupportOperationException {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addRequiredProperty("description");
		return description;
	}
	
	@Override
	public Item newDelegate() {
		return new Item();
	}
	
	@Override
	public Item save(Item t) {
		t.setOwner(Context.getAuthenticatedUser());
		Item item = Context.getService(BasicexampleService.class).saveItem(t);
		return item;
	}
	
	@PropertyGetter("display")
	public String getDisplayString(Item item) {
		return item.getDescription();
	}
	
}
