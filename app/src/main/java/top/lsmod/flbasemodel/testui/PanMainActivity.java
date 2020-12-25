package top.lsmod.flbasemodel.testui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import com.mobsandgeeks.saripaar.ValidationError;

import java.util.List;

import butterknife.BindView;
import top.lsmod.basemodel.FlBaseAppActivity;
import top.lsmod.basemodel.pen.GridPaintActivity;
import top.lsmod.basemodel.pen.PaintActivity;
import top.lsmod.basemodel.pen.config.PenConfig;
import top.lsmod.flbasemodel.R;

public class PanMainActivity extends FlBaseAppActivity {
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.iv_show)
    ImageView ivShow;
    private boolean isPermissionOk = false;

    @Override
    protected Object initLayout() {
        return R.layout.activity_pan_main;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            isPermissionOk = false;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
        } else {
            isPermissionOk = true;
        }
    }

    @Override
    protected void initData() {

    }


    /**
     * 空白签批
     *
     * @param view
     */
    public void openBlank(View view) {
        if (!isPermissionOk) {
            return;
        }
        String base64 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAmAAAAIkCAYAAAC5opeqAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAAEnQAABJ0Ad5mH3gAACQTSURBVHhe7daLsqygsQDQ/P9P54abmHg86iD4aOi1qqg9M4pboGn6H/8EAOBVCjAAgJcpwAAAXqYAAwB4mQIMAOBlCjAAgJcpwAAAXqYAAwB4mQIMeN0//vGP0wYwO5kOCEUBBmQg0wGhKMCADGQ6IBQFGJCBTAeEogADMpDpgFAUYEAGMh0QigIMyECmA0JRgAEZyHRAKAowIAOZDghFAQZkINMBoSjAgAxkOiAUBRiQgUwHhKIAAzKQ6YDXlSLrrAHMTqYDQlGAARnIdEAoCjAgA5kOCEUBBmQg0wGhKMCADGQ6IBQFGJCBTAeEogADMpDpgFAUYEAGMh0QigIMyECmA0JRgAEZyHRAKAowIAOZDghFAQZkINMBoSjAgAxkOiAUBRiQgUwHvK4UWWcNYHYyHRCKAgzIQKYDQlGAARnIdEAoCjAgA5kOCEUBBmQg0wGhKMCADGQ6IBQFGJCBTAeEogADMpDpgFAUYEAGMh0QigIMyECmA0JRgAEZyHRAKAowIAOZDghFAQZkINMBrytF1lkDmJ1MB4SiAAMykOmAUBRgQAYyHRCKAgzIQKYDQlGAARnIdEAoCjAgA5kOCEUBBmQg0wGhKMCADGQ6IBQFGJCBTAeEogADMpDpgFAUYEAGMh0QigIMyECmA0JRgAEZyHTA60qRddYAZifTAaEowIAMZDogFAUYkIFMB4SiAAMykOmAUBRgQAYyHRCKAgzIQKYDQlGAARmEzHQlAWdowN/sDWi3PWdmbLMIW4DNLsMYoYW9Ae1m3z8KsIcpwGBs6/gun5dWw96AdrPvn5nGF3IkGRKwQ4aZLfG9jfOauLc3oN3s+2em8YUcSYYE7JBhZkt8b+O8Ju7tDWg3+/6ZaXwhR5IhATtkmNkS39s4r4l7ewPazb5/ZhpfyJG0THDpc9R+qemzd09prXr6QnR7e2T7+awBbXr2z3Yf/npWzf1795TWqqdvNCFHcvfi/Frw9bWj+7a/nz2vRm9/mJW9Ae1a9k/pc9Tv7Hnra7X9z55Xo7d/JCFH0jPBZ32Prq1/r/lcHD2rVm9/GFFN3Nsb0O7q/unZk+vfaz4XR8+q1ds/kpAj6Zngs75H19a/n33eth69/SGy7V5Zt19q7gH2Xd0/Pftt3ffs87b16O0fSciR9EzwWd+ja+X3dduz/f3ovlq9/SG6vRiviXt7A9pd3T89+630Xbc929+P7qvV2z+SkCPpmeC9vuW3s2eurx3dt/397Hk1evvDrOwNaHd1//Tst3Xfo+dsf+/5f0Vv/0hCjqRngkvfbftlfc/Z523r0dt/cddzIAoxzUzejuer/6/n/dZ9zz5vW4/e/pGEHEnPBO/1/bXo62vLvds+68+Lvd9q9fRd3PEMiEZcM5s3Y/rq/+p5t3Xf8nndFuvPi73favX0jSbkSJ5anKNr5fd1W2w/r6+vr7X4uj9EJbaZ0VtxffX/9LxX6btui+3n9fX1tRa9/SMJOZKeCT7re3Rt/fvVz616n3HHO0BEYptZvRHbV/9Hzzut+1793OqOZ0QRciQ9E3zW9+ja+vdfn7d/W/X07/3fEJn4ZmZPx/fV59fcf3TP+vdfn7d/W/X2jyTkSHom+Kjv2TPX184+n32/qrVvz/+EEYhxZvdkjLc8+6xP7bWzz2ffr+rpG03Ikdy9OGfP215bfz/6fIee5939LhCJ+GZmT8d36/OP+tX+vv5+9PkOdz/vSyFH0jLBpc9RO7O9vnzf/r1b73Ofei94Q4nfswYzeiO2e/7Heg8u7cj22vJ9+/duTz33CyFH8uYEr4Nl2xZ719atRWu/tTueAdGIa2b0Vly//X/K321b7F1btxat/SIKOZJRJrjnPe8a40zBCIWYZjZvxvQI+6fnHWfKDyFHkiEB3znGX8/KMJ/MQ7wyippYfTueZ98/M40v5EgyJOC7x3j0vAxzyVzELCM5i9cvYnn2/TPT+EKOpExwhna37TOf+B/wNHHLaPZy+ldxvLzLzG0WMt1kluCcKUjJRewyKvmXK0TJhGx+RiZ+GZn4pZZImZQkwKjELqMSu1whWiYmGTAiccuIxC1XiZjJSQqMRswyGjFLC1GTgOTASMQrIxGvtBI5SdQkCYmECMQhEciZPE30JHKWLCQS3lTi7axBBGexKE7pJYKS2UsaEgmRiEcikTN5iihKaJ08JBKiEZNEso1H8cldRFJSJYlIJEQkLolmiUmxyZ1EU1IKMKISl0QkLrmbiEpOUiEaMUk0YpIniCokF0IRj0QiHnmKyOL/STJEIRaJQizyJNHFf9UkGwmJp4kxnlQbX+KQp4kw/nCWdCQk3iDOeNqvGBODvEGU8Ze95CMh8RaxxhuO4kz88RaRxq51EpKQeJN44y3bWBN7vEm0cagkIwmJt4k53rTEm7jjbSKOQwownrLE1lGDN4k5viDq+Ely4k3ijTeJN74i8qgiSfEWscZbxBpfEn1Uk6x4gzjjDeKMr4lALqlJWhIbPcQPrWpjR4wRgSjksrPkJbHRSwyxaImFX33EF1GIRJrsJbHWxFb6SYosxAJFTxwc9RVbRCIaabZNZq3JTVJkTTywjoHyuSUmtn3EFdGISLosSa01ue0lSYkyN+uf27L+21zQEhfrZ0E0opJuV5PbUVJcf5cw87L2HMVAS2yIJ6ISmdziapKrSbASZ07WPZ/aNb8aG2KJyEQnt7kjOW5/k0Dzseb5lDX/te5X40IcEZ0I5VYtSW/bR+LMzfrns6z53tqX367GhBhiBKKU2/UmS8lzfmWNzxp59a6/+GEUIpVH1CTB9T1Hn8nH+rPEwNW8IHYYiWjlMWfJcHutfF8auYkBir04OIsNccNoRCyP2ibF8l2i5Iz44CwG9nKImGFEopbHLclRkqSGOKGGvMLoRC6vkCSpJVaoJVYYmejlNZIlNcQJNcQJoxPBvErSzKOs9bbVqL2PvMQIMxDFvE7yzGFvnWvWXnxwRnwwC5HMJyTRuR2trwKMHmKDmYhmPuMwntfRullz9ogLMhLRfOosqUq4Yyvrt201au9jLmfrLiaYkajmc3vJVcLNy9rnJReQicgmhHWSlXDntaxt+XvWyGm79mKBmYluwnD4zmNZy732S809zGtZf3HA7EQ4YdQe0Ixhby1r1lcMIAbIQJQTjuSbm/XPzfqThUgnJEk4L2ufl7UnE9FOWJJxTtY9J+tONiKe0GqSssQ9F+s5j9q1tOZkJOoJ7yw5S9zzsaZz+bWe1pusRD5D2EvSEvecrOt8jtbUWpOZ6GcY62Qtcc/L2s5pu67WmezsAIZSkrbEPTfrO69lba0xKMAYjAJsftZ3btYX/s1OYEiS+Lys7bysLfyP3cCwJPNxlbU7a8zHusKf7AiGJqnPx5rOx5rC3+wKhleT3B0A47BWY6hdJ+sJ++wMpnCW5B0AY7Fe4/i1VtYSjtkdTGMv2TsAxmPNxnK0XtYRztkhTGWb9B0C47Fm47Hv4Dq7hOksyd8hMCbrNib7Dq6xU5iSQ2Bc1m5c1g7q2S1My2EwJus2JusG19gxTM2hMB5rNh5rBtfZNUzP4TAW6zUW6wVt7BxScEiMw1qNw1pBO7uHNGoOi9oDpdy3NO5lTu+1jtXaua25r/ZZwD47iFTODo2rB4oDqF2Zu7PGfbbzWTu/Z/dZI+hnF5HO3oF05UC5ej/XmNt77c1n7Rzvxbr1gXvYSaS0HCJXDqL137Xy29LoZx77bedwb06vzPNyr7WB+9hNpHX1MPl1iDmc7mEe+5U53M7jr++/WBe4lx1Faj2HUO+Bxj7z2G+Zw70YXdoVV+8HfrOrSK/lcNn2cUDdx1zeq3c+rQc8w86Cf7l6yKzvd0Ddy3zeb5nTnjgH7mV3wX+0HE4OqPuZ02e0xDfwHDsMVmoOHQfTs8zv/dZzKsYhBrsMNs4OHwfT88zx88Q4fM9Ogx17h5CD6R3m+R1iHL5lt8GB9WHkYHqPuX7Hdp7NO7zLjoMT5VByML3LfL9nmWtzDu+z6+CEAux95vtd5hu+YedBBYfUvZbC9qjxDnMN37H7oJLD6h3m+R3mGb5lB8IFDq3nmePnmWP4nl0IF9UcXg64duauTe28mV+IwU6EBmeHmAOuj/lr92vuzC3EYTdCo73DzAHXzxz2OZo/8wqx2JHQYX2oOeDuYR77befQnEI8diV0KoebA+4+5vIeyzyaT4jJzoROCrB7mcv7mEuIy+6Emzjs7mEe72EeITY7FG7k0OtnDvuZQ4jPLoWbOfz6mL8+5g/GYKfCA2oOQQflPvPyt9o5MXcwDrsVHnJ2GGY/KMv4zxp/+zUv5g3GYsfCg/YORQflOfNz7GhuzBmMx66Fh20PR4flOfNzTjzBHOxceMFySDosfzNHv4knGJ/dCy9xWNYxT3XME4zNDoYXOTR/M0e/mSMYn10ML3N4njM/58wPzMFOhg84RI+Zm2PmBuZhN8NHHKb7zMs+8wJzsaPhQzWHaraD13j/pviC+djV8LGzw3XUg3f93uXz0mqMOuYeZ2POOB+QgZ0NAWwP2fJ95IN3efe9cf0y8rh7lHG3zBcwJrsbglgO2xkO3aOx1IxthvH3OJo7YC52OAQyy6F7VESsfz9r2ZkDmJ9dDsHMcPjuFVO145ph/D2yjx+ysNMhoMyHsLEDGdjtEFTWw9i4gQzseAgs46FszEAGdj0EV3M4z3SAZxuL4gtysvNhAGeHdOsBHvXgj/hePe/0xNoB47P7YRB7h3VvYRCxAIj2Tne8z94zIs498B4ZAAayPrR7DvC7nvOESO9z17tsnxNtzoH3yQIwmHJ433GAr58RqSCI8i7b9yjfl9Zi6dfaH5iLTACDaS0C9vpELAYivdPyLtt3an3HiPMNfEM2gEFdPcz37o9YEER7p7vmLeJcA9+REWBgVw71cu/6/u33KCK+09bVdxxhTMC7ZAUYXEsx8HVBsLzDUYuo9f2ijgf4lswAE6g55EcpBEZ4z9p3VHwBR2QHmMTZYT9SITDKu/56T8UXcEaGgInsHfqjFQIjve/Ruyq+gF9kCZjM+vAfsRAY7Z2376v4AmrIFDChUgSMWgiM+N7LOyu+gFqyBUxIAfY+xRdwhYwBExuxKPDOQAayBkxutOLA+wIZyByQwEhFgncFMpA9IIlRigXvCWQgg0Aiv4qGCEXF1+9Q8/8VX0AvWQSSOSoeohQVEd7j7B0UX8AdZBJIaFtEvF1UlP931iLYe5co7waMTzaBpJZiIlpREfV9FF/AnWQUSCxiUeGdgAxkFUguWnHhfYAMZBYgVJHhXYAMZBfg/0UpNrwHkIEMA/xXhKLDOwAZyDLAH2qKjycLlK+frfgC3iDTAH85K0KeLlC+fL7iC3iLbAPs2hYj5fsbBcpb/2NvfABvkXGAQ0tR8mZx8sX/UnwBb5N1gFNvFyez/z+AQuYBfnqzSJn1fwGsyT5AlTuLlfKss/aGt/4PwB4ZCKj2RtEyy/8AOCMLAZc8XbyM/nyAGjIRcFlNEdNa6DzZT/EFRCEbAU3OipmeQuepvoovIBIZCWi2V9T0FjpP9Fd8AdHISkCXdXFzR6FzdwGm+AIikpmAbqXIuavQueM5yzMUX0BUshPQLVoBVii+gMhkKOA2dxQ9UZ4B8CRZCrhVb/HzdX+AN8hUH9geEOX7ts1sO74s496zHvu6ja5nDF/1jWKJgXXLaDvuLHOxHefyfd2Yg5V82d4GyrShtuPPPBfFzOOtGdvePa0xMctczjKOHmUO1vNw9Hk2y9iyjDc7K/uB7YYq39dtdusxbsebYfxry5qv20yOxrMd87Zt7f229uv6SNbzMNO4rlqP/ejzrLbjXTfmYTU/8GsTzb7J1uPbjlWCmW8O9sZzNsaja1d/n8Xs4zuyHvfR51mdjTFrPMzISn7g1waafYOtx7cdq+Qy5xycrfnW2fXttRnnaivDGPesx330eVZnY8waDzOykh/4tYFm32Dr8W3HKrnMOwdlXDVj+3XPcn3WedrKMs6t9biPPs/qbIxZ42FGVvID2w306/ts1uMrn7ffM9kb76xzsF3rI3fdM6rt2GYe65n1uI8+z+psvBnGn4WVfFnZPEtbO/p9NkfjzDD2I+s5yTAHv8bYe30GSyxkGOuevfFnmI/1uNdj3fuN8VlN4HVnB0nrNYCRfJrNWpKpPm19CvOgT9HSp7j7fx1du/p7cfe7HdGnrU9hHvQpWvoUrf0i+3REswVJ5D6FedCnaOlTPPG/9q7X/rb2xLvt0aetT2Ee9Cla+hSt/SL7dESzBUnkPoV50Kdo6VM89b+29/z6vuepd9vSp61PYR70KVr6FK39Ivt0RLMFSeQ+hXnQp2jpUzz5v9b3HX0+8+S7renT1qcwD/oULX2K1n6RfTqi2YIkcp/CPOhTtPQpnv5fy73bvzWefreFPm19CvOgT9HSp2jtF9mnI5otSCL3KcyDPkVLn+KN/1XuX9oVb7xboU9bn8I86FO09Cla+0X26YjKhGqapmmapv1qs5lvRMCwZk62AGuyHBDCUnRt/wLMSIYDPrcuto4+A8xEdgM+tS2yfn0HmIHMBjyipnDau6f2ty2FGjASGQt4zFlRdHTt6u+F4gsYjawFPGqvOGotprbXyvez+wGikrmAx62LpF8FU+11hRcwMhkMeEUpmGqKprvuAYhMFgNeUVs03X0fQEQyGPC4K8XSU/cCRCJ7AY+6WiQ9fT9ABDIX8JiW4qj0OWpHzq4BRCRrAZfVFDytRdGT/RRqQBSyEdDkrJjpKXSe6qv4AiKRkYBme0VNb6HzRH/FFxCNrAQ02xY2dxQ6dz9D8QVEJDMBXZYC565C587nKL6AqGQnoNudhY4CDMhAdgK63F3kRH8ewB1kJqDZE8XNKM8E6CErAU2eKmpGey5ACxkJ+K/aIuXJYubrZyvUgDfINMAffhUgTxcoXz5f8QW8RbYB/nJUiLxRoHz1PxRfwJtkHGDXtiB5q0Ap/+eo3Wn7vLufD3BGxgEOLUXJm8XJF/9L8QW8TdYBTr1dnMz+/wAKmQc49EVxkuV/ArnJOsCur4qSbP8XyEnGAf7yZTGS9X8Ducg2wB++LkKy/38gB5kGkqgpLCIUHyO8gyIN6CWLQCJnhUOUoiL6eyi+gDvIJDCY3gKg9N8+I1JREfldet+t9I80PuA7MgEM5M7De3lWtIIg6vv0vte6f7QxAu+TBWAQ2wP8jkM8YiEw8zutnxNxnMB7ZAAYwHJYl793HeJRC4CZ32v7jKhjBZ5n98Mgjg7rlkM88sFf3u2ofa3lHbZ9IowD+J5MAIO7eqBHLwBme7/t/dHHB7xDJoCB9RYDEc34juv7Rxgf8DyZAAZUDvGrh/ooB/9M77ncU/4uDaCQDWASZ4f7SAf/LO+q2ALOyBAwkb1Df7RCYPT3Ld9HGwPwPlkCJrM+/EcsBEZ+Z4UXUEu2gAmVQmDUYsB7AxnIGDChkYsB7w5kIFvAZEYvArw/kIFMAROZ4fA3BiADWQImMcuhbxxABjIEBFdzkM902Gcbi0INcrLzYQBnh/RsB3im8Si+IC+7Hwaxd1jPeICXMR21Ue29+8jjAfrJADCI7YE96wGeYVyKL0AWgIEsB/fMB/jsY1N8AYVMAIOZ/QBXgAEZyAQwkAyHtzECGcgCMIgsh7ZxAhnIADCATIe1sQIZ2P3wkdrDN9shbbx/U6jBfOxq+NCvgzXjwWvMf1J8wZzsbPjY0QGb9eA17v9RfMG87G4IYHvQZj54jf1/FGAwL7sbglgO2+yHbs349wqVpY1uGcMMYwGO2eEQiEP39xzsXZ+taBEHMD+7HIJw6P5bmYejtlzfOrs2qpnGAvzNDocAHLb/82su9q4vv802j+IC5mV3w8ccsn+qmY9yz7otavqOZsYxAf/a2//5C3zA4fo3c/I3cwLzsavhATUHpkN1n3nZ92tezBuMxY6Fh5wdiA7LY+bm2NHcmDMYj10LDyoH4/ZwdFieMz/nxBPMwc6FFyyHpMPyN3P0m3iC8dm98BKHZR3zVMc8wdjsYHiBw7KeuapnrmBcdi88zCF5jfm6xnzBmOxceJDD8Tpzdp05g/HYtfAQh2Ib89bGvMFY7Fi4qOagcxi2K3N31DhXM0fmEWKwE6HB2SHmgOtj/vqITRiD3QiN9g4zB1w/c9hvO4flu3mFWOxI0lkOozsOpXX/3mfxb+bxHss83jGf5RnWBe5lR5HO9iDpPVgcTvcyl/e5ay6tCdzPriKdvcOk54BxON3LfN6rdz6tBzzDziKF7SGyd6i0HDQOp/uZ0/tdndPl/r1+5belAe3sIFLYOzB+ff/FAfQM8/qMO+J7/Zt1gj52ECksh8X20Cjfl3bF1fupZ26f0xPn277WCfrYQaRzdnDUHCoOnmeZ32e1xPiv78B1dhEpLQfI3kFydrg4eJ5njp93NcbXv1kfuIedRFo9hxDPMc/vuBrj5Zq1gfvYTaT06yDZXnfwvMdcv2c91+Yd3mXHwYHlQHIwvavM91HjfuYWvmHXwQkH0/vM+bsUYPANuw4OOJS+Yd6/Yd7hXXYc7HAYfcfcf8fcw3vsNthwCH3L/H/L/MM77DRSqD1UHD7fswbfq1kD6wR97CDS+HVgOFBisA4xnK2DNYJ+dhGpHB0cDpQ4rEUce2thfeAedhLpbA8QB0os1iMW+wWeYSeR0nKIOEzisSbx2C9wP7uJtBwmMVmXmKwL3MuOIiWHSVzWJi5rA/exm0jHIRKb9YnN+sA97CRScXjEV9boqBGDtYB+dhFpODTGYJ3GYJ2gjx3E8GoOAofFOKzVOH6tlbWEY3YHUzhL9A6BsVivsRytl3WEc3YI0ygJf5v0HQLjsWbjse/gOruE6SzJ3yEwJus2JvsOrrFTmJJDYFzWblzWDurZLUzHITA26zc26wd17BSmIvmPzxqOzxrCb3YJ05D052Ad52Ad4ZwdwhQk+3lYy3lYSzhmdxBaTQKX5OdiPediD8M+UU94Z8lZ4p6PNZ2PPQx/E/kMYS9JS9xzKut61BjXdv2sKdmJfoaxTtYS97ys7byWtbXG8K998J+/MISSuCXvuVnfuVlf+Dc7gaFI3vOzxvOzxqAAYyCSdg7WOQfrTHZ2AEOQrPOw1nlYazIT/YQnSedivXOx3mQl8rnN1URac7/knI81z0cuICMRTbeSGFuT41k/CTcn657Tnbmg3L80iEp00mWd4FqT3t79V5/BPKx9XnfmAnFEdCKUZusEt012V5JfT1/mY/1zW69/SyyUPtt+YoqIRCVdlsTWm/COnkM+v2Lg6LrYmUdZy5b1PIsN8UE0IpLLtolsL7G1JDsJkuJXHJTrZ43xXV3Ls/vXv195JjxNNHLZWbJbXE10EiOLJb722nKdHK6u9dH969/FD1GIRC5bEtg2kZXvS7vi6v3MTTyw1hIPe33EFdGISLr0JjVJkS0xwdbVmNi7X1wRjYik25LYtn9/kRDZUxMX5Z69xrxq1ne5ZxsP2+8QgYjkFtvk9ivZSYYc6YkdcTW3q2tffhMTRCUy6XaU4K7+DsWv+Di7Lrbmt7fG1p0RiVoetU2MEiW/1MRIuWevkcN2ra09IxK1PG5JjpIkNcQJNeQVRidyeYUkSS2xQi2xwshEL4+TJLlCvHCFeGFUIpdHSY5cJWa4SswwIlHLYyRFWogbWogbRiNieYRkSCuxQyuxw0hEK5fUJDhJkB4lfo4a/PIrTsQRUYhELjtLYJIbvcQQvY5iSGwRiWikSUlk22QmuXEHccQd5CeiE5F0WZKa5MZdxBJ3kZ+ITFTSTXLjTuKJO4knohKZdJHcuJuY4m5iiohEJc0kNZ4grniCuCIaEUkTyYyniC2eIraIRDRymSTGk8QXTxJfRCES+a+axCR58TQxxtPkOiIQYfzhLOlISLxBnPEGuY6viTL+spd8JCTeItZ4i1zHl0Qaf9kmIAmJN4k33rSON7HHm0Qbu5ZEJCHxthJzRw2eIL74gojjkITEF8Qdb1OA8QURxy7JiK+IPb4i9niTaOMvkhBfEn98SfzxFpHGHyQfviYG+ZoY5A2iLIHaZCLpEIE4JIKaOBSr9BA9SfxKFBIJUYhFojiLRXFKLxGUyFHCkEiIRDwSyV48ilHuIIqS2SYOiYRoxCTRrGNSfHIXkZTQkkAkEiISl0RU4lJscifRlJREQlRik4gUYNxNNCUkiRCZ+CQy8cldRFIykgfRiVGiE6PcQRQlImkwghKnRw2iEI/0EkGDq00CkgWjEKuMoiZWxTNHRMYEfm1wCYCRiFdGchavYpkzomMSRxtdAmA0YpbR7MWsOOYXETKR7YaXABiRuGVE8i9XiZDJLJve5mdUYpdRyb9cIUomZPMzMvHLyMQvtUTKZGx+RieGGZ0YpoYomYhNzwzEMTMQx/wiQiZhszMLscwsxDJnRMcEbHJmIp6ZiXjmiMgIrGbj2tzMRkwzm18xLeZzsurBnW1Mm5YZiWtmdBTX4j0vKz+AskG3m9SmZVZLvO81GNk2hsV0blZ/IMtmtWmZmfhmZvI4CxEwGJuW2YlxZifGKUQBEIrDCchApgNCUYABGch0QCgKMCCDkJmuJOAMDfibvQHttufMjG0WYQuw2WUYI7SwN6Dd7PtHAfYwBRjkZW9AOwXYOEKORAEGef3aG0fX7SmYfx/MNL6QI8mQSB0WsO/X3ijXzxpkNvsemGl8IUeSIYk6KGDfr71h78Cx2ffHTOMLOZI7JvjKM8q927a1d09prXr6wszsDWh3df8sZ9lRO1Nz/949pbXq6RtNyJHcMcFXFnl931Gf7e+1zz7S2x9mVbM39vbj0iCznj2w1/fseetrR/dtfz97Xo3e/pGEHMldC1T7nPV9NZ+L2mcf6e0Psyp746gt17fOrkEmPXvgqG/N7zWfi6Nn1ertH0nIkdy5QDXPOrp/+3nbevT2h1n92ht715ff7Cuy69kDR31rfj/7vG09evtHEnIkPRO87VvzrHLPuu3Z/n50X63e/jCrX3tj7/rym31Fdj174Kjv2e/rtmf7+9F9tXr7RxJyJD0TvNf31/PW14/u3f7+65m/9PaHWf3aG3vXl9/sK7Lr2QNHfWt+r+17dF+t3v6RhBxJ6wTXBsDW+vrZ523r0dsfZlWzN9b7cH1/TV+YWc8eOOpb8/vZ523r0ds/kpAjaZ3gs36118rndVusPy/2fqvV0xdmZm9AuyfOpZrfy+d1W6w/L/Z+q9XTN5qQI2mZ4F99zq6Xa+u22H5eX19fa9HbH2Zlb0C7nv1z1Pfs93VbbD+vr6+vtejtH0nIkbRM8LLAv9qe9e9XP7e64xkwI3sD2vXsn6O+Nb9f/dzqjmdEEXIkLRNc0+fonvXvvz5v/7bq7Q+zsjegXc/+Oepb8/uvz9u/rXr7RxJyJC0TXNPn6J7172efz75f1dMXZmZvQLsnzqWa388+n32/qqdvNCFH0jLBNX327tn+tv5+9PkOdz8PZmFvQLue/bPX9+h529/X348+3+Hu530p5EhaJrimz94929+W79u/d3vquTA6ewPaXd0/5f6zdmR7bfm+/Xu3p577hZAjeXOC18GybYu9a+vWorUfzM7egHZv7Z/l/5S/27bYu7ZuLVr7RRRyJKNMcM97zhREcKeyN44acG6EfdLzjjPlgZAjyZBoHSawz96AdrPvHwXYwxRgkJe9Ae0UYOMIORIFGORlb0A7Bdg4Qo6kTHCGBvzN3oB223NmxjYLmQ4IZaYEC3BEpgNCUYABGch0QCgKMCADmQ4IRQEGZCDTAaEowIAMZDogFAUYkIFMB4SiAAMykOmAUBRgQAYyHRBKKcCOGsAsZDQgFIUWkIFMB4SiAAMykOmAUBRgQAYyHRCKAgzIQKYDQlGAARnIdEAoCjAgA5kOCEUBBmQg0wGhKMCADGQ6IBQFGJCBTAeEogADMpDpgFAUYEAGMh0QigIMyECmA0IpBdhRA5iFjAaEotACMpDpgFAUYEAGMh0QigIMyECmA0JRgAEZyHRAKAowIAOZDghFAQZkINMBoSjAgAxkOiAUBRiQgUwHhKIAAzKQ6YBQFGBABjIdEIoCDMhApgNCUYABGch0QCilADtqALOQ0YBQFFpABjIdEIoCDMhApgNCUYABGch0QCgKMCADmQ4IRQEGZCDTAaEowIAMZDogFAUYkIFMB4SiAAMykOmAUBRgQAYyHRCKAgzIQKYDQlGAARnIdEAoCjAgA5kOCKUUYEcNYBYyGgDAyxRgAAAvU4ABALxMAQYA8DIFGADAyxRgAAAvU4ABALxMAQYA8DIFGADAyxRgAAAvU4ABALxMAQYA8DIFGADAyxRgAAAvU4ABALzqn//8P2XxpjiLv3GdAAAAAElFTkSuQmCC";
        Intent intent = new Intent(this, PaintActivity.class);
//        intent.putExtra("background", Color.WHITE);//画布背景色，默认透明，也是最终生成图片的背景色
//        intent.putExtra("width", 800); //画布宽度，最大值3000，默认占满布局
//        intent.putExtra("height", 800);//画布高度，最大值3000，默认占满布局
        intent.putExtra("crop", false);   //最终的图片是否只截取文字区域
        intent.putExtra("format", PenConfig.FORMAT_PNG); //图片格式
        intent.putExtra("drawPic", base64);
//        intent.putExtra("image", imagePath); //初始图片
        startActivityForResult(intent, 100);
    }

    /**
     * 田字格签批
     *
     * @param view
     */
    public void openGrid(View view) {
        if (!isPermissionOk) {
            return;
        }
        Intent intent = new Intent(this, GridPaintActivity.class);
        intent.putExtra("background", Color.WHITE);
        intent.putExtra("crop", true);
        intent.putExtra("fontSize", 50);  //手写字体大小
        intent.putExtra("format", PenConfig.FORMAT_PNG);
        intent.putExtra("lineLength", 6);   //每行显示字数（超出屏幕支持横向滚动）
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            String savePath = data.getStringExtra(PenConfig.SAVE_PATH);
            Log.i("king", savePath);
            tvResult.setText(savePath);
            Bitmap bitmap = BitmapFactory.decodeFile(savePath);
            if (bitmap != null) {
                ivShow.setImageBitmap(bitmap);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 100) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户同意使用write
                isPermissionOk = true;
            } else {
                //用户不同意，自行处理即可
                finish();
            }
        }
    }

    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

    }
}
