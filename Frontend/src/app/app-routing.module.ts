import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { NotfoundComponent } from './demo/components/shared/components/notfound/notfound.component';
import { AppLayoutComponent } from "./layout/app.layout.component";
import {authGuard} from "./demo/components/shared/guards/authGuard";
import {ForbiddenComponent} from "./demo/components/shared/forbidden/forbidden.component";

@NgModule({
    imports: [
        RouterModule.forRoot([
            {
                path: '', component: AppLayoutComponent,
                children: [
                    { path: 'motorpolicy', loadChildren: () => import('./demo/components/motorpolicy/motor-policy.module').then(m => m.MotorPolicyModule)  },
                    { path: 'shared', loadChildren: () => import('./demo/components/shared/shared.module').then(m => m.SharedModule)  },
                    { path: 'users', loadChildren: () => import('./demo/components/module-users/module-users.module').then(m => m.ModuleUsersModule)  },
                    { path: 'clients', loadChildren: () => import('./demo/components/client/client.module').then(m => m.ClientModule)  },
                ]
            ,
                //canActivate: [authGuard]

},
            { path: 'auth', loadChildren: () => import('./demo/components/auth/auth.module').then(m => m.AuthModule)  },
            { path: 'notfound', component: NotfoundComponent },
            { path: 'forbidden', component: ForbiddenComponent },
            { path: '**', redirectTo: '/notfound' },
        ], { scrollPositionRestoration: 'enabled', anchorScrolling: 'enabled', onSameUrlNavigation: 'reload' })
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {

}
