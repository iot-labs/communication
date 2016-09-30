module body(w,l,h) {
    difference() {
        hull() {
            for(y = [h/2,-h/2])
                for(x = [l/2, -l/2])
                    translate([0,x,y])resize(newsize = [w,                20,20])sphere(r=10);
            translate([0,0,-h/2-5])cube([w-10,l-10,10],center=        true);
        }
        translate([0,0,10])cube([w-10,l,h+10],center=true);
         translate([30,-2,-3])rotate([0,90,0])cylinder(h=20,r1=5,r2=5);
    }
}

module cap(w,l,h) {
    difference() {
        hull() {
            for(y = [h/2,-h/2])
                for(x = [l/2, -l/2])
                    translate([0,x,y])resize(newsize = [w,                20,20])sphere(r=10);
        }
        hull() {
            for(y = [h/2,-h/2])
                for(x = [l/2, -l/2])
                    translate([0,x,y-5])resize(newsize = [w,                20,20])sphere(r=10);
        }
        translate([0,0,-10])cube([120,100,50],center=true);
    }  
}

translate([0,0,-30])body(92,72,40);
translate([0,0,30])cap(92,72,40);

