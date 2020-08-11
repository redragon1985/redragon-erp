package org.jasig.action;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.jasig.cas.util.UniqueTicketIdGenerator;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

public class ProvideCasLoginTicketAction extends AbstractAction{  

        private static final String PREFIX = "LT";  

        //private final Logger logger = Logger.getLogger(ProvideCasLoginTicketAction.class);


        @NotNull  
        private UniqueTicketIdGenerator ticketIdGenerator;   

        @Override  
        protected Event doExecute(RequestContext context) throws Exception {  

            final HttpServletRequest request = WebUtils.getHttpServletRequest(context);  

            if (request.getParameter("lt_flag") != null && request.getParameter("lt_flag").equalsIgnoreCase("true") &&
                request.getParameter("cas_client_key") != null	&& request.getParameter("cas_client_key").equals("aph2_cas_redragon")) {  
                final String loginTicket = this.ticketIdGenerator.getNewTicketId(PREFIX);  
                //this.logger.debug("--------------Generated login ticket :" + loginTicket);
                WebUtils.putLoginTicket(context, loginTicket); 
                return result("loginTicketRequested");
            }  
            return result("continue");
        }
        
        
        
        /*
         * setter,getter方法
         */
        public void setTicketIdGenerator(final UniqueTicketIdGenerator generator) {  
            this.ticketIdGenerator = generator;  
        }
}
