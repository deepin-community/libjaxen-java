/*
 * $Header$
 * $Revision: 1277 $
 * $Date: 2007-01-05 19:25:43 +0100 (Fri, 05 Jan 2007) $
 *
 * ====================================================================
 *
 * Copyright 2007 bob mcwhirter & James Strachan.
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
 * $Id: BinaryExprTest.java 1277 2007-01-05 18:25:43Z elharo $
 */



package org.jaxen.test;

import javax.xml.parsers.ParserConfigurationException;

import org.jaxen.JaxenException;
import org.jaxen.dom.DOMXPath;

import junit.framework.TestCase;

/**
 * <p>
 *  Test for various kinds of binary expressions.
 * </p>
 * 
 * @author Elliotte Rusty Harold
 * @version 1.1.1
 *
 */
public class BinaryExprTest extends TestCase
{

    public void testBooleanPrecedence() 
     throws JaxenException, ParserConfigurationException {
      
        // Note how the parentheses change the precedence and the result
        DOMXPath xpath1 = new DOMXPath("false() and (false() or true())");
        Boolean result1 = (Boolean) xpath1.evaluate(null);
        assertFalse(result1.booleanValue());
        DOMXPath xpath2 = new DOMXPath("false() and false() or true()");
        Boolean result2 = (Boolean) xpath2.evaluate(null);
        assertTrue(result2.booleanValue());
        
        String expr = xpath1.getRootExpr().getText();
        DOMXPath xpath3 = new DOMXPath(expr);
        Boolean result3 = (Boolean) xpath3.evaluate(null);
        assertEquals(expr, result1, result3);
        assertFalse(expr, result3.booleanValue());
      
    }

}