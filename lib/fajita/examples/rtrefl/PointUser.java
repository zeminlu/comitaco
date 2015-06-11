/*
 * This code was generated by ojc.
 */
/*
 * PointUser.oj
 */
package examples.rtrefl;


public class PointUser
{

    public static void main( String[] args )
    {
        Point p = new Point( 1, 2 ){
        };
        p.mt = new VerboseRTMetaObject( p ){
        };
        p.write_name( "MyFavorite" );
        int x = p.read_x() + 1;
        p.write_y( x * 2 + p.read_y() );
        System.out.println( "distance = " + p.distanceFromOrigin() );
    }

}