/**
 * boilerpipe
 *
 * Copyright (c) 2009 Christian Kohlschütter
 *
 * The author licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//#include <cstdlib>

package de.l3s.boilerpipe.demo;

import java.net.URL;

import de.l3s.boilerpipe.extractors.*;

/**
 * Demonstrates how to use Boilerpipe to get the main content as plain text.
 * Note: In real-world cases, you'd probably want to download the file first using a fault-tolerant crawler.
 * 
 * @author Christian Kohlschütter
 * @see HTMLHighlightDemo if you need HTML as well.
 */
public class Oneliner {
    public static void main(final String[] args) throws Exception {
	String contingut ="";
	final URL url = new URL(
        			args[0]
        		);

        // This can also be done in one line:
	 try{
         	contingut = ArticleExtractor.INSTANCE.getText(url);
		if(contingut.length()<50){
			try{
				contingut = DefaultExtractor.INSTANCE.getText(url);
				if(contingut.length()<50){
					try{
						contingut = CommonExtractors.CANOLA_EXTRACTOR.getText(url);
					}
					catch(Exception error6){
						System.out.println("Error commonExtractor1");
					}
				}
			}
			catch(Exception error4){
				System.out.println("Error DefaultExtractor");
				try{
					contingut = CommonExtractors.CANOLA_EXTRACTOR.getText(url);
				}
				catch(Exception error5){
					System.out.println("Error commonExtractor");
				}
			}
		}
	 }catch(Exception error){
	 	System.out.println("Error ArticleExtractor");
	 	try{
			contingut = DefaultExtractor.INSTANCE.getText(url);
		 }catch(Exception error2){
	 		System.out.println("Error DefaultExtractor");
	 		 try{
	 			contingut = CommonExtractors.CANOLA_EXTRACTOR.getText(url);
			 }catch(Exception error3){
	 			System.out.println("Error commonExtractor");
			 }
		}
	 }
	 finally{
	 	System.out.println(contingut);
	 }


        // Also try other extractors!
//        System.out.println(DefaultExtractor.INSTANCE.getText(url));
//       System.out.println(CommonExtractors.CANOLA_EXTRACTOR.getText(url));
    }
}
