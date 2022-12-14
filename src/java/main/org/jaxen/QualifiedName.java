/*
 * $Header$
 * $Revision: 1154 $
 * $Date: 2006-06-01 15:19:30 +0200 (Thu, 01 Jun 2006) $
 *
 * ====================================================================
 *
 * Copyright 2000-2002 bob mcwhirter & James Strachan.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 * 
 *   * Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 * 
 *   * Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 * 
 *   * Neither the name of the Jaxen Project nor the names of its
 *     contributors may be used to endorse or promote products derived 
 *     from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * ====================================================================
 * This software consists of voluntary contributions made by many 
 * individuals on behalf of the Jaxen Project and was originally 
 * created by bob mcwhirter <bob@werken.com> and 
 * James Strachan <jstrachan@apache.org>.  For more information on the 
 * Jaxen Project, please see <http://www.jaxen.org/>.
 * 
 * $Id: QualifiedName.java 1154 2006-06-01 13:19:30Z elharo $
 */

package org.jaxen;

import java.io.Serializable;

/** A local name (that matches the XML NCName production) and a namespace URI
 *  with which the local name is qualified.
 *
 *  @author Erwin Bolwidt ( ejb@klomp.org )
 */
class QualifiedName implements Serializable
{

    private static final long serialVersionUID = 2734958615642751535L;
    
    private String namespaceURI;
    private String localName;

    /** Constructs a QualifiedName object.
     *
     *  @param namespaceURI namespace URI that qualifies the name, or
     *                      <code>null</code> for default namespace
     *  @param localName    local name that is qualified by the namespace uri;
     *                      must not be <code>null</code>
     */
    QualifiedName( String namespaceURI, String localName )
    {
        if (namespaceURI == null) namespaceURI = "";
        this.namespaceURI = namespaceURI;
        this.localName = localName;
    }

    public int hashCode()
    {
        return ( localName.hashCode() ^ namespaceURI.hashCode() );
    }

    public boolean equals( Object o )
    {
        // Because this class is package protected and used in only
        // two other classes, it's never actually compared to anything 
        // other than another QualifiedName. No instanceof test is
        // necessary here.
        QualifiedName other = (QualifiedName) o;
        return ( namespaceURI.equals(other.namespaceURI) &&
                 other.localName.equals(localName) );
    }
    
    /**
     * @return James Clark's namespace form
     */
    String getClarkForm() {
        if ("".equals(namespaceURI)) return localName;
        else return "{" + namespaceURI + "}" + ":" + localName;
    }
    
}

