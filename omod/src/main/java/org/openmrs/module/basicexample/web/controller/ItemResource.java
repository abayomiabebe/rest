/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicexample.web.controller;

import java.util.List;
import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.web.resource.impl.MetadataDelegatingCrudResource;
import org.openmrs.module.basicexample.Item;
import org.openmrs.module.basicexample.api.BasicexampleService;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;

import org.springframework.beans.factory.annotation.Autowired;

@Resource(name = RestConstants.VERSION_1 + "/item", supportedClass = Item.class, supportedOpenmrsVersions = { "2.0.*",
        "2.1.*", "2.2.*", "2.3.*", "2.4.* " })
public class ItemResource extends DataDelegatingCrudResource<Item> {
	
	@Autowired
	BasicexampleService basicexampleService;
	
	@Override
	public Item getByUniqueId(String string) {
		List<Item> its = Context.getService(BasicexampleService.class).getAllItems();
		Item it = new Item();
		return it;
	}
	
	public NeedsPaging<Item> doGetAll(RequestContext context) {
		return new NeedsPaging<Item>(Context.getService(BasicexampleService.class).getAllItems(), context);
	}
	
	@Override
	protected void delete(Item t, String string, RequestContext rc) throws ResponseException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	@Override
	public void purge(Item t, RequestContext rc) throws ResponseException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
	public Item newDelegate() {
		return new Item();
	}
	
	@Override
	public Item save(Item t) {
		Item item = basicexampleService.saveItem(t);
		return item;
	}
	
	@PropertyGetter("display")
	public String getDisplayString(Item item) {
		return item.getDescription();
	}
	
}
