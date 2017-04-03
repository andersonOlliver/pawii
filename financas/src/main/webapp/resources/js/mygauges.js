/* 
 * Various gauge implementations
 */
var gauge1, gauge2;
      
window.onload = function(){
    
    /* JustGage implementation (uses justage.js and raphael.js) */
    gauge1 = new JustGage({
      id: "gauge1", 
      value: 0, 
      min: 0,
      max: 100,
      title: "Update from Server",
      label: "count",
      shadowOpacity: 1,
      shadowSize: 0,
      shadowVerticalOffset: 10,
      levelColors: [
            "#FFFF00",
            "#FF9900",
            "#FF0000"
          ]    
    });

    /* Canvas gauge implementation (see gauge.js) */
    gauge2 = new Gauge({
        renderTo    : 'gauge2',
        width       : 300,
        height      : 300,
        glow        : true,
        units       : 'Km/h',
        title       : false,
        minValue    : 0,
        maxValue    : 220,
        majorTicks  : ['0','20','40','60','80','100','120','140','160','180','200','220'],
        minorTicks  : 2,
        strokeTicks : false,
        highlights  : [
                { from : 0,   to : 50, color : 'rgba(0,   255, 0, .15)' },
                { from : 50, to : 100, color : 'rgba(255, 255, 0, .15)' },
                { from : 100, to : 150, color : 'rgba(255, 30,  0, .25)' },
                { from : 150, to : 200, color : 'rgba(255, 0,  225, .25)' },
                { from : 200, to : 220, color : 'rgba(0, 0,  255, .25)' }
        ],
        colors      : {
                plate      : '#222',
                majorTicks : '#f5f5f5',
                minorTicks : '#ddd',
                title      : '#fff',
                units      : '#ccc',
                numbers    : '#eee',
                needle     : { start : 'rgba(240, 128, 128, 1)', end : 'rgba(255, 160, 122, .9)' }
        }
    });
    
    gauge.onready = function() {	
        gauge.setValue( Math.random() * 220);	
    };
        
    gauge2.draw();

};

