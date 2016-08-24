/**
 *
 */

if(!String.prototype.startsWith){
	String.prototype.startsWith = function(prefix, toffset) {
		  var i = 0;
		  if(toffset && (typeof toffset === 'number')) {
		    i = toffset;
		  }
		  return this.slice(i).indexOf(prefix) === 0;
		};
}